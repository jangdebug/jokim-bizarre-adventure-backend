package com.jokim.sivillage.api.bridge.eventmedialist.vo.in;

import lombok.Getter;

@Getter
public class AddEventMediaListRequestVo {

    private String eventCode;
    private String mediaCode;
    private Boolean isThumbnail;

}
