package com.jokim.sivillage.api.review.vo.out;

import com.jokim.sivillage.api.review.dto.out.ReviewSummaryResponseDto.EvaluationSummary;
import lombok.*;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ReviewSummaryResponseVo {
    private Double starAverage;  // 별점 평균
    private List<EvaluationSummary> evaluation;  // 평가 항목 정보
}