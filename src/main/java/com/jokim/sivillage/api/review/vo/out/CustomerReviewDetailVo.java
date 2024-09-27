package com.jokim.sivillage.api.review.vo.out;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CustomerReviewDetailVo {
    private String productCode;
    private String content;
    private LocalDateTime created_at;
    private String reviewCode;

}
