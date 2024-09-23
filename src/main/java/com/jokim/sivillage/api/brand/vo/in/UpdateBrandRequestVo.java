package com.jokim.sivillage.api.brand.vo.in;

import lombok.Getter;

@Getter
public class UpdateBrandRequestVo {

    private String brandCode;
    private String englishName;
    private String koreanName;
    private String englishInitial;
    private String koreanInitial;

}
