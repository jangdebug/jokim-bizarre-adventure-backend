package com.jokim.sivillage.api.brand.vo.in;

import jakarta.persistence.Column;
import lombok.Getter;

@Getter
public class AddBrandRequestVo {

    private String englishName;
    private String koreanName;
    private String englishInitial;
    private String koreanInitial;

}
