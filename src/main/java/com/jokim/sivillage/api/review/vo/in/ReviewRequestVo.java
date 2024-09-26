package com.jokim.sivillage.api.review.vo.in;

import com.jokim.sivillage.api.review.domain.ReviewType;
import lombok.Getter;

@Getter
public class ReviewRequestVo {
    private String productCode;
    private ReviewType reviewType;
    private String content;
    private Double starPoint;
}
