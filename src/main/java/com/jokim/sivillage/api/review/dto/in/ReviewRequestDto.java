package com.jokim.sivillage.api.review.dto.in;

import com.jokim.sivillage.api.review.domain.Review;
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
    private String type;
    private String content;
    private Double starPoint;
    private Boolean state;
    private String url;

    public static ReviewRequestDto toDto(ReviewRequestVo reviewRequestVo){
        return ReviewRequestDto.builder()
                .productCode(reviewRequestVo.getProductCode())
                .type(reviewRequestVo.getType())
                .content(reviewRequestVo.getContent())
                .starPoint(reviewRequestVo.getStarPoint())
                .url(reviewRequestVo.getUrl())
                .build();
    }


    public Review toEntity(String uuid){
        return Review.builder()
                .productCode(productCode)
                .uuid(uuid)
                .reviewCode(UUID.randomUUID().toString())
                .type(type)
                .content(content)
                .starPoint(starPoint)
                .state(true)
                .build();
    }

}
