package com.jokim.sivillage.api.review.vo.out;

import com.jokim.sivillage.api.review.dto.out.ReviewResponseDto;
import lombok.*;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ReviewSummaryResponseVo {
    private Double starAverage;
    private List<ReviewResponseDto.Evaluation> evaluation;
    private List<String> image; // List<String>으로 변경
}
