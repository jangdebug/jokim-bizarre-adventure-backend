package com.jokim.sivillage.api.review.dto.in;

import com.jokim.sivillage.api.review.domain.Review;
import com.jokim.sivillage.api.review.domain.ReviewType;
import com.jokim.sivillage.api.review.vo.in.ReviewRequestVo;
import lombok.*;

import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ReviewRequestDto {
    private String productCode;
    private String uuid;
    private String reviewCode;
    private ReviewType reviewType;
    private String content;
    private Double starPoint;
    private Boolean state;

    public static ReviewRequestDto toDto(ReviewRequestVo reviewRequestVo){
        return ReviewRequestDto.builder()
                .productCode(reviewRequestVo.getProductCode())
                .reviewType(reviewRequestVo.getReviewType())
                .content(reviewRequestVo.getContent())
                .starPoint(reviewRequestVo.getStarPoint())
                .build();
    }


    public Review toEntity(String uuid){
        return Review.builder()
                .productCode(productCode)
                .uuid(uuid)
                .reviewCode(UUID.randomUUID().toString())
                .reviewType(reviewType)
                .content(content)
                .starPoint(starPoint)
                .state(true)
                .build();
    }

}
