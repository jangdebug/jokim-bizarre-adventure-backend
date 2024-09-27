package com.jokim.sivillage.api.brand.vo.out;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GetBrandSummaryResponseVo {

    private String englishName;
    private String koreanName;

    @JsonProperty("logoMediaUrl")
    private String mediaUrl;

}
