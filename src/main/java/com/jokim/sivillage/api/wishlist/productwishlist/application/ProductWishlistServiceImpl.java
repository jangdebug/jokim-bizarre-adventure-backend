package com.jokim.sivillage.api.wishlist.productwishlist.application;

import com.jokim.sivillage.api.wishlist.productwishlist.domain.ProductWishlist;
import com.jokim.sivillage.api.wishlist.productwishlist.dto.ProductWishlistRequestDto;
import com.jokim.sivillage.api.wishlist.productwishlist.infrastructure.ProductWishlistRepository;
import com.jokim.sivillage.common.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ProductWishlistServiceImpl implements ProductWishlistService {

    private final ProductWishlistRepository productWishlistRepository;
    private final JwtTokenProvider jwtTokenProvider;

    @Transactional
    @Override
    public void addProductWishlist(ProductWishlistRequestDto productWishlistRequestDto) {

        String uuid = jwtTokenProvider.validateAndGetUserUuid(productWishlistRequestDto.getAccessToken());
        Long id = productWishlistRepository.findByUuidAndProductCode(
                uuid, productWishlistRequestDto.getProductCode()).map(ProductWishlist::getId).orElse(null);

        productWishlistRepository.save(productWishlistRequestDto.toEntity(id, uuid, true));
    }

}
