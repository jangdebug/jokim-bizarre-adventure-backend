package com.jokim.sivillage.api.bridge.eventmedialist.vo.out;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GetThumbnailEventMediaListResponseVo {

    private String mediaCode;
    private String mediaUrl;
    private String mediaType;

}
