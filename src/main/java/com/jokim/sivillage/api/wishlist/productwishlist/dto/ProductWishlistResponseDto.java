package com.jokim.sivillage.api.wishlist.productwishlist.dto;

import com.jokim.sivillage.api.wishlist.productwishlist.domain.ProductWishlist;
import com.jokim.sivillage.api.wishlist.productwishlist.vo.out.GetAllProductWishlistResponseVo;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProductWishlistResponseDto {

    private String productCode;

    public static ProductWishlistResponseDto toDto(ProductWishlist productWishlist) {
        return ProductWishlistResponseDto.builder()
                .productCode(productWishlist.getProductCode())
                .build();
    }

    public GetAllProductWishlistResponseVo toVo() {
        return GetAllProductWishlistResponseVo.builder()
                .productCode(productCode)
                .build();
    }

}
