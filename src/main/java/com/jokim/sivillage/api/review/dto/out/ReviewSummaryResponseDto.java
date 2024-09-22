package com.jokim.sivillage.api.review.dto.out;

import com.jokim.sivillage.api.review.vo.out.ReviewSummaryResponseVo;
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
    private List<String> image;  // 이미지 정보

    // of 메서드를 통해 빌더 패턴을 사용한 인스턴스 생성
    public static ReviewSummaryResponseDto of(Double starAverage,
                                              List<EvaluationSummary> evaluations,
                                              List<String> images) {
        return ReviewSummaryResponseDto.builder()
                .starAverage(starAverage)
                .evaluation(evaluations)
                .image(images)
                .build();
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    public static class EvaluationSummary {
        private String name;   // 평가 항목 이름
        private String value;  // 평가 항목 값
        private Double rate;  // 평가 항목 비율
    }

}
