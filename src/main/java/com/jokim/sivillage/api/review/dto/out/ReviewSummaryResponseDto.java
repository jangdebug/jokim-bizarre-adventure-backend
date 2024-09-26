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

    // 조인 메서드 수정: EvaluationItemValue와 조인하여 value 값 가져오기
    public static List<EvaluationSummary> fetchEvaluationSummaries(
        JPAQueryFactory queryFactory,
        String productCode) {
        QProductStatistic productStatistic = QProductStatistic.productStatistic;
        QEvaluationItemName evaluationItemName = QEvaluationItemName.evaluationItemName;
        QEvaluationItemValue evaluationItemValue = QEvaluationItemValue.evaluationItemValue;

        return queryFactory
            .select(Projections.fields(EvaluationSummary.class,
                evaluationItemName.name,
                productStatistic.evaluationItemNameRate.as("rate"),
                evaluationItemValue.value)) // value 값 추가
            .from(productStatistic)
            .join(evaluationItemName).on(productStatistic.evaluationItemNameId.eq(evaluationItemName.id))
            .join(evaluationItemValue).on(productStatistic.evaluationItemValueId.eq(evaluationItemValue.id)) // evaluationItemValue와 조인
            .where(productStatistic.productCode.eq(productCode))
            .fetch();
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