package com.jokim.sivillage.api.review.dto.out;

import com.jokim.sivillage.api.review.domain.Review;
import com.jokim.sivillage.api.review.vo.out.ReviewResponseVo;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ReviewResponseDto {
    private String productCode;
    private String reviewCode;
    private Double starPoint;
    private String type;
    private Boolean isBest;
    private String customerEmail;
    private LocalDateTime modifyDate;
    private Integer likeCount;
    private String productOption;
    private String content;

    // 리스트 형태의 필드 추가
    private List<Evaluation> evaluation; // 평가 정보를 담는 리스트

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    public static class Evaluation {
        private String name; // 평가 항목 이름
        private String value; // 평가 항목 값
    }

    public static ReviewResponseDto fromReview(
        Review review,
        List<Evaluation> evaluations
    ) {
        return ReviewResponseDto.builder()
            .productCode(review.getProductCode())
            .reviewCode(review.getReviewCode())
            .starPoint(review.getStarPoint())
            .type(review.getType())
            .isBest(false)
            .customerEmail("anonymous")
            .modifyDate(review.getUpdatedAt())
            .likeCount(0)
            .productOption("default")
            .content(review.getContent())
            .evaluation(evaluations)
            .build();
    }

    public ReviewResponseVo toVo(){
        return ReviewResponseVo.builder()
            .productCode(productCode)
            .reviewCode(reviewCode)
            .starPoint(starPoint)
            .type(type)
            .isBest(false)
            .customerEmail("anonymous")
            .modifyDate(modifyDate)
            .likeCount(0)
            .productOption("default")
            .content(content)
            .evaluation(evaluation)
            .build();
    }


}

