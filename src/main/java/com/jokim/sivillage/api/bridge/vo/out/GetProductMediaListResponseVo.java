package com.jokim.sivillage.api.bridge.vo.out;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GetProductMediaListResponseVo {

    private String mediaCode;
    private Boolean isThumbnail;

}
