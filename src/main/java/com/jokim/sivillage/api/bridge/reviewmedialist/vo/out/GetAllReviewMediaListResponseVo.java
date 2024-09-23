package com.jokim.sivillage.api.bridge.reviewmedialist.vo.out;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GetAllReviewMediaListResponseVo {

    private String mediaCode;
    private String mediaUrl;
    private String mediaType;

}
