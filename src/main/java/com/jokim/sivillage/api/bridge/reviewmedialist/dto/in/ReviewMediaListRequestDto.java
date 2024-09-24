package com.jokim.sivillage.api.bridge.reviewmedialist.dto.in;

import com.jokim.sivillage.api.bridge.reviewmedialist.domain.ReviewMediaList;
import com.jokim.sivillage.api.bridge.reviewmedialist.vo.in.AddReviewMediaListRequestVo;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ReviewMediaListRequestDto {

    private String reviewCode;
    private String mediaCode;

    public static ReviewMediaListRequestDto toDto(
        AddReviewMediaListRequestVo addReviewMediaListRequestVo) {

        return ReviewMediaListRequestDto.builder()
            .reviewCode(addReviewMediaListRequestVo.getReviewCode())
            .mediaCode(addReviewMediaListRequestVo.getMediaCode())
            .build();
    }

    public ReviewMediaList toEntity() {
        return ReviewMediaList.builder()
            .reviewCode(reviewCode)
            .mediaCode(mediaCode)
            .build();
    }

}
