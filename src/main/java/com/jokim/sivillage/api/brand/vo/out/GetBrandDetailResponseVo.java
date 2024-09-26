package com.jokim.sivillage.api.brand.vo.out;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GetBrandDetailResponseVo {

    private String brandCode;
    private String englishName;
    private String koreanName;
    private String englishInitial;
    private String koreanInitial;

}
