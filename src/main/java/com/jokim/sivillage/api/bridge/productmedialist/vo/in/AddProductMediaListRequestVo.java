package com.jokim.sivillage.api.bridge.productmedialist.vo.in;

import lombok.Getter;

@Getter
public class AddProductMediaListRequestVo {

    private String productCode;
    private String mediaCode;
    private Boolean isThumbnail;

}
