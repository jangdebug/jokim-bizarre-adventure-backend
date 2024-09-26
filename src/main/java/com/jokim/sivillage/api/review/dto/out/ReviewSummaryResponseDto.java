package com.jokim.sivillage.api.review.dto.out;

import com.jokim.sivillage.api.review.domain.QEvaluationItemName;
import com.jokim.sivillage.api.review.domain.QEvaluationItemValue;
import com.jokim.sivillage.api.review.domain.QProductStatistic;
import com.jokim.sivillage.api.review.vo.out.ReviewSummaryResponseVo;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.*;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ReviewSummaryResponseDto {
    private Double starAverage;  // 별점 평균
    private List<EvaluationSummary> evaluation;  // 평가 항목 정보

    public static ReviewSummaryResponseDto of(Double starAverage, List<EvaluationSummary> evaluations) {
        return ReviewSummaryResponseDto.builder()
            .starAverage(starAverage)
            .evaluation(evaluations)
            .build();
    }

    public ReviewSummaryResponseVo toVo() {
        return ReviewSummaryResponseVo.builder()
            .starAverage(starAverage)
            .evaluation(evaluation)
            .build();
    }

    // 내부 클래스
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    public static class EvaluationSummary {
        private String name;  // 평가 항목 이름
        private String value; // 평가 항목의 값
        private Integer rate; // 평가 항목의 비율
    }
}