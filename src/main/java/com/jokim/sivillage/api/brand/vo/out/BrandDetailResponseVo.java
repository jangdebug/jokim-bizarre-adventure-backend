package com.jokim.sivillage.api.brand.vo.out;

import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BrandDetailResponseVo {

    private String brandCode;
    private String englishName;
    private String koreanName;
    private String englishInitial;
    private String koreanInitial;

}
