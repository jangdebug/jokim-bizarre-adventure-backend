package com.jokim.sivillage.api.category.vo.out;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetCategoryResponseVo {

    @JsonProperty("categoryCode")
    private String categoryCode;

    @JsonProperty("categoryName")
    private String name;

}
