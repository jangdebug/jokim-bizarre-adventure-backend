package com.jokim.sivillage.api.bridge.reviewmedialist.dto;

import com.jokim.sivillage.api.bridge.reviewmedialist.domain.ReviewMediaList;
import com.jokim.sivillage.api.bridge.reviewmedialist.vo.GetReviewMediaListResponseVo;
import lombok.Builder;

@Builder
public class ReviewMediaListResponseDto {

    private String mediaCode;

    public static ReviewMediaListResponseDto toDto(ReviewMediaList reviewMediaList) {

        return ReviewMediaListResponseDto.builder()
            .mediaCode(reviewMediaList.getMediaCode())
            .build();
    }

    public GetReviewMediaListResponseVo toVo() {

        return GetReviewMediaListResponseVo.builder()
            .mediaCode(mediaCode)
            .build();
    }

}
