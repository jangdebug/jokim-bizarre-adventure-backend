package com.jokim.sivillage.api.bridge.brandmedialist.vo.out;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GetAllBrandMediaListsResponseVo {

    private String mediaCode;
    private String mediaUrl;
    private String mediaType;
    private Boolean isLogo;

}
