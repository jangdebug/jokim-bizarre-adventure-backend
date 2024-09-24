package com.jokim.sivillage.api.review.like.dto;

import com.jokim.sivillage.api.review.like.vo.GetReviewLikeListResponseVo;
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
