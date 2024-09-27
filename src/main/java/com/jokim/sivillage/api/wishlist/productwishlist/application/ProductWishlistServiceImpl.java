package com.jokim.sivillage.api.wishlist.productwishlist.application;

import com.jokim.sivillage.api.wishlist.productwishlist.domain.ProductWishlist;
import com.jokim.sivillage.api.wishlist.productwishlist.dto.ProductWishlistRequestDto;
import com.jokim.sivillage.api.wishlist.productwishlist.dto.ProductWishlistResponseDto;
import com.jokim.sivillage.api.wishlist.productwishlist.infrastructure.ProductWishlistRepository;
import com.jokim.sivillage.api.wishlist.productwishlist.infrastructure.ProductWishlistRepositoryCustom;
import com.jokim.sivillage.common.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProductWishlistServiceImpl implements ProductWishlistService {

    private final ProductWishlistRepository productWishlistRepository;
    private final ProductWishlistRepositoryCustom productWishlistRepositoryCustom;
    private final JwtTokenProvider jwtTokenProvider;

    @Transactional
    @Override
    public void addProductWishlist(ProductWishlistRequestDto productWishlistRequestDto) {

        String uuid = jwtTokenProvider.validateAndGetUserUuid(productWishlistRequestDto.getAccessToken());
        Long id = productWishlistRepository.findByUuidAndProductCode(
                uuid, productWishlistRequestDto.getProductCode()).map(ProductWishlist::getId).orElse(null);

        productWishlistRepository.save(productWishlistRequestDto.toEntity(id, uuid, true));
    }

    @Transactional(readOnly = true)
    @Override
    public List<ProductWishlistResponseDto> getAllProductWishlists(String accessToken, Integer recentMonths) {

        return productWishlistRepositoryCustom.getAllProductWishlists(
                jwtTokenProvider.validateAndGetUserUuid(accessToken), recentMonths)
                .stream().map(ProductWishlistResponseDto::toDto).toList();
    }

    @Transactional(readOnly = true)
    @Override
    public ProductWishlistResponseDto getProductWishlistState(String accessToken, String productCode) {

        boolean isChecked = productWishlistRepository.existsByUuidAndProductCodeAndIsChecked(
                jwtTokenProvider.validateAndGetUserUuid(accessToken), productCode, true);

        return ProductWishlistResponseDto.toDto(isChecked);
    }

    @Transactional
    @Override
    public void deleteProductWishlist(ProductWishlistRequestDto productWishlistRequestDto) {    // Soft Delete

        String uuid = jwtTokenProvider.validateAndGetUserUuid(productWishlistRequestDto.getAccessToken());
        Long id = productWishlistRepository.findByUuidAndProductCode(
                uuid, productWishlistRequestDto.getProductCode()).map(ProductWishlist::getId).orElse(null);

        if(id == null) return;
        productWishlistRepository.save(productWishlistRequestDto.toEntity(id, uuid, false));
    }

}
