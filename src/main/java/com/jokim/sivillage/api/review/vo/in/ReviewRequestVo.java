package com.jokim.sivillage.api.review.vo.in;

import lombok.Getter;

@Getter
public class ReviewRequestVo {
    private String productCode;
    private String type;
    private String content;
    private Double starPoint;
    private String url;
}
