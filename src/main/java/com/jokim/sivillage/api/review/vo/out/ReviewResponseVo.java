package com.jokim.sivillage.api.review.vo.out;

import com.jokim.sivillage.api.review.dto.out.ReviewResponseDto.Evaluation;
import java.time.LocalDateTime;
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
public class ReviewResponseVo {
    private String productCode;
    private String reviewCode;
    private Double starPoint;
    private String type;
    private Boolean isBest;
    private String parsedEmail;
    private LocalDateTime modifyDate;
    private Integer likeCount;
    private String productOption;
    private String content;
    // 리스트 형태의 필드 추가
    private List<Evaluation> evaluation; // 평가 정보를 담는 리스트

}
