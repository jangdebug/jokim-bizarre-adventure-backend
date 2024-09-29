package com.jokim.sivillage.api.basket.vo.in;

import lombok.Getter;

@Getter
public class UpdateBasketItemCheckRequestVo {

    private String basketCode;
    private Boolean isChecked;

}
