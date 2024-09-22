package com.jokim.sivillage.api.wishlist.brandwishlist.application;

import com.jokim.sivillage.api.wishlist.brandwishlist.domain.BrandWishlist;
import com.jokim.sivillage.api.wishlist.brandwishlist.dto.BrandWishlistRequestDto;
import com.jokim.sivillage.api.wishlist.brandwishlist.infrastructure.BrandWishlistRepository;
import com.jokim.sivillage.common.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class BrandWishlistServiceImpl implements BrandWishlistService {

    private final BrandWishlistRepository brandWishlistRepository;
    private final JwtTokenProvider jwtTokenProvider;

    @Transactional
    @Override
    public void addBrandWishlist(BrandWishlistRequestDto brandWishlistRequestDto) {

        String uuid = jwtTokenProvider.validateAndGetUserUuid(brandWishlistRequestDto.getAccessToken());
        Long id = brandWishlistRepository.findByUuidAndBrandCode(uuid,
                brandWishlistRequestDto.getBrandCode()).map(BrandWishlist::getId).orElse(null);

        brandWishlistRepository.save(brandWishlistRequestDto.toEntity(id, uuid, true));
    }

}
