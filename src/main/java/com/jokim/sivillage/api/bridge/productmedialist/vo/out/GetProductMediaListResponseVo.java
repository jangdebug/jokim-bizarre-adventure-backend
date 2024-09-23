package com.jokim.sivillage.api.bridge.productmedialist.vo.out;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GetProductMediaListResponseVo {

    private String mediaCode;
    private String mediaUrl;
    private String mediaName;
    private String mediaType;
    private Boolean isThumbnail;

}
