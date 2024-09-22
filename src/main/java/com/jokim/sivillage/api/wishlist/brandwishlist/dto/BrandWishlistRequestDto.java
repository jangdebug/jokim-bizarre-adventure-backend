package com.jokim.sivillage.api.wishlist.brandwishlist.dto;

import com.jokim.sivillage.api.wishlist.brandwishlist.domain.BrandWishlist;
import com.jokim.sivillage.api.wishlist.brandwishlist.vo.in.AddBrandWishlistRequestVo;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BrandWishlistRequestDto {

    private String accessToken;
    private String brandCode;

    public static BrandWishlistRequestDto toDto(
            AddBrandWishlistRequestVo addBrandWishlistRequestVo, String accessToken) {  // add wishlist

        return BrandWishlistRequestDto.builder()
                .accessToken(accessToken)
                .brandCode(addBrandWishlistRequestVo.getBrandCode())
                .build();
    }

    public static BrandWishlistRequestDto toDto(String accessToken, String brandCode) { // soft delete wishlist
        return BrandWishlistRequestDto.builder()
                .accessToken(accessToken)
                .brandCode(brandCode)
                .build();
    }

    public BrandWishlist toEntity(Long id, String uuid, Boolean isChecked) {
        return BrandWishlist.builder()
                .id(id)
                .uuid(uuid)
                .brandCode(brandCode)
                .isChecked(isChecked)
                .build();
    }

}
