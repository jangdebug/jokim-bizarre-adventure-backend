package com.jokim.sivillage.api.wishlist.reviewlike.dto;

import com.jokim.sivillage.api.wishlist.reviewlike.vo.GetReviewLikeListResponseVo;
import lombok.Builder;

@Builder
public class ReviewLikeListResponseDto {

    private Boolean isChecked;

    public static ReviewLikeListResponseDto toDto(Boolean isChecked) {
        return ReviewLikeListResponseDto.builder()
            .isChecked(isChecked)
            .build();
    }

    public GetReviewLikeListResponseVo toVo() {
        return GetReviewLikeListResponseVo.builder()
            .isChecked(isChecked)
            .build();
    }

}
