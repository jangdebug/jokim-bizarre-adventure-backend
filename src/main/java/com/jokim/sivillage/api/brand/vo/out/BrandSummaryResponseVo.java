package com.jokim.sivillage.api.brand.vo.out;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BrandSummaryResponseVo {

    @JsonProperty("brandName")
    private String englishName;

    @JsonProperty("badgeMediaCode")
    private String mediaCode;

}
