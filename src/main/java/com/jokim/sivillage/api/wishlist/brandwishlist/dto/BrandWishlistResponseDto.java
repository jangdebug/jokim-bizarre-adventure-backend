package com.jokim.sivillage.api.wishlist.brandwishlist.dto;

import com.jokim.sivillage.api.wishlist.brandwishlist.domain.BrandWishlist;
import com.jokim.sivillage.api.wishlist.brandwishlist.vo.out.GetAllBrandWishlistResponseVo;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BrandWishlistResponseDto {

    private String brandCode;

    public static BrandWishlistResponseDto toDto(BrandWishlist brandWishlist) {
        return BrandWishlistResponseDto.builder()
                .brandCode(brandWishlist.getBrandCode())
                .build();
    }

    public GetAllBrandWishlistResponseVo toVoForBrandCode() {
        return GetAllBrandWishlistResponseVo.builder()
                .brandCode(brandCode)
                .build();
    }

}
