package com.jokim.sivillage.api.basket.vo.in;

import lombok.Getter;

import java.util.List;

@Getter
public class DeleteBasketItemsRequestVo {

    private List<String> basketCodeList;

}
