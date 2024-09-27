package com.jokim.sivillage.api.basket.vo.in;

import lombok.Getter;

@Getter
public class AddToBasketRequestVo {

    private String productCode;
    private String productOptionCode;
    private Short quantity;

}
