package com.jokim.sivillage.api.wishlist.productwishlist.dto;

import com.jokim.sivillage.api.wishlist.productwishlist.domain.ProductWishlist;
import com.jokim.sivillage.api.wishlist.productwishlist.vo.in.AddProductWishlistRequestVo;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProductWishlistRequestDto {

    private String accessToken;
    private String productCode;

    public static ProductWishlistRequestDto toDto(
            AddProductWishlistRequestVo addProductWishlistRequestVo, String accessToken) {  // add wishlist

        return ProductWishlistRequestDto.builder()
                .accessToken(accessToken)
                .productCode(addProductWishlistRequestVo.getProductCode())
                .build();
    }

    public static ProductWishlistRequestDto toDto(String accessToken, String productCode) { // soft delete wishlist
        return ProductWishlistRequestDto.builder()
                .accessToken(accessToken)
                .productCode(productCode)
                .build();
    }

    public ProductWishlist toEntity(Long id, String uuid, Boolean isChecked) {
        return ProductWishlist.builder()
                .id(id)
                .uuid(uuid)
                .productCode(productCode)
                .isChecked(isChecked)
                .build();
    }

}
