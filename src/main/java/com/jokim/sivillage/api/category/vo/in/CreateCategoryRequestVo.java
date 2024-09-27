package com.jokim.sivillage.api.category.vo.in;

import lombok.Getter;

@Getter
public class CreateCategoryRequestVo {

    private String parentCategoryCode;
    private String name;

}
