package com.jokim.sivillage.api.basket.vo.in;

import lombok.Getter;

@Getter
public class UpdateBasketItemQuantityRequestVo {

    private String basketCode;
    private Short quantity;

}
