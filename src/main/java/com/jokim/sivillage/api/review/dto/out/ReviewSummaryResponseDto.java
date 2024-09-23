package com.jokim.sivillage.api.review.dto.out;

import com.jokim.sivillage.api.review.domain.ProductStatistic;
import com.jokim.sivillage.api.review.domain.QEvaluationItemName;
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

    // 조인 메서드
    public static List<EvaluationSummary> fetchEvaluationSummaries(
        JPAQueryFactory queryFactory,
        String productCode) {
        QProductStatistic productStatistic = QProductStatistic.productStatistic;
        QEvaluationItemName evaluationItemName = QEvaluationItemName.evaluationItemName;

        return queryFactory
            .select(Projections.fields(EvaluationSummary.class,
                evaluationItemName.name,
                productStatistic.evaluationItemNameRate.as("rate")))
            .from(productStatistic)
            .join(evaluationItemName).on(productStatistic.evaluationItemNameId.eq(evaluationItemName.id))
            .where(productStatistic.productCode.eq(productCode))
            .fetch();
    }

    public ReviewSummaryResponseVo toVo(){
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
        private Integer rate; // 평가 항목의 비율
    }
}



