package com.jokim.sivillage.api.wishlist.productwishlist.dto;

import com.jokim.sivillage.api.wishlist.productwishlist.domain.ProductWishlist;
import com.jokim.sivillage.api.wishlist.productwishlist.vo.out.GetAllProductWishlistResponseVo;
import com.jokim.sivillage.api.wishlist.productwishlist.vo.out.GetProductWishlistStateResponseVo;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProductWishlistResponseDto {

    private String productCode;
    private Boolean isChecked;

    public static ProductWishlistResponseDto toDto(ProductWishlist productWishlist) {
        return ProductWishlistResponseDto.builder()
                .productCode(productWishlist.getProductCode())
                .build();
    }

    public static ProductWishlistResponseDto toDto(Boolean isChecked) {
        return ProductWishlistResponseDto.builder()
                .isChecked(isChecked)
                .build();
    }

    public GetAllProductWishlistResponseVo toVoForProductCode() {
        return GetAllProductWishlistResponseVo.builder()
                .productCode(productCode)
                .build();
    }

    public GetProductWishlistStateResponseVo toVoForIsChecked() {
        return GetProductWishlistStateResponseVo.builder()
                .isChecked(isChecked)
                .build();
    }

}
