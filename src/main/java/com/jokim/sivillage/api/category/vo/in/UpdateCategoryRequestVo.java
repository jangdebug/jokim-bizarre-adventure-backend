package com.jokim.sivillage.api.category.vo.in;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class UpdateCategoryRequestVo {

    private String parentCategoryCode;
    private String categoryCode;
    private String name;

}
