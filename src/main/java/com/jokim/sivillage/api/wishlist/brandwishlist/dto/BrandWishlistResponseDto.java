package com.jokim.sivillage.api.wishlist.brandwishlist.dto;

import com.jokim.sivillage.api.wishlist.brandwishlist.domain.BrandWishlist;
import com.jokim.sivillage.api.wishlist.brandwishlist.vo.out.GetAllBrandWishlistResponseVo;
import com.jokim.sivillage.api.wishlist.brandwishlist.vo.out.GetBrandWishlistStateResponseVo;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BrandWishlistResponseDto {

    private String brandCode;
    private Boolean isChecked;

    public static BrandWishlistResponseDto toDto(BrandWishlist brandWishlist) { // get all brand wishlist
        return BrandWishlistResponseDto.builder()
                .brandCode(brandWishlist.getBrandCode())
                .build();
    }

    public static BrandWishlistResponseDto toDto(Boolean isChecked) {
        return BrandWishlistResponseDto.builder()
                .isChecked(isChecked)
                .build();
    }

    public GetAllBrandWishlistResponseVo toVoForBrandCode() {
        return GetAllBrandWishlistResponseVo.builder()
                .brandCode(brandCode)
                .build();
    }

    public GetBrandWishlistStateResponseVo toVoForIsChecked() {
        return GetBrandWishlistStateResponseVo.builder()
                .isChecked(isChecked)
                .build();
    }

}
