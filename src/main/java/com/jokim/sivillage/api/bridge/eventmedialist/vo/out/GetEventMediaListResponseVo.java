package com.jokim.sivillage.api.bridge.eventmedialist.vo.out;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GetEventMediaListResponseVo {

    private String mediaCode;
    private Boolean isThumbnail;

}
