package com.jokim.sivillage.api.category.vo.in;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class CreateCategoryRequestVo {

    private String parentCategoryCode;
    private String name;

}
