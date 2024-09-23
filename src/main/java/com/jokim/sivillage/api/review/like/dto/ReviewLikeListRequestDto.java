package com.jokim.sivillage.api.review.like.dto;

import com.jokim.sivillage.api.review.like.domain.ReviewLikeList;
import com.jokim.sivillage.api.review.like.vo.AddReviewLikeListRequestVo;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ReviewLikeListRequestDto {

    private String accessToken;
    private String reviewCode;

    public static ReviewLikeListRequestDto toDto(
        AddReviewLikeListRequestVo addReviewLikeListRequestVo, String accessToken) {   // add review like

        return ReviewLikeListRequestDto.builder()
            .accessToken(accessToken)
            .reviewCode(addReviewLikeListRequestVo.getReviewCode())
            .build();
    }

    public ReviewLikeList toEntity(Long id, String uuid, Boolean isChecked) {
        return ReviewLikeList.builder()
            .id(id)
            .uuid(uuid)
            .reviewCode(reviewCode)
            .isChecked(isChecked)
            .build();
    }

}
