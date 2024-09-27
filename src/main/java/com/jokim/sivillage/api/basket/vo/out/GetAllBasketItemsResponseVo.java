package com.jokim.sivillage.api.basket.vo.out;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class GetAllBasketItemsResponseVo {

    private String productCode;
    private String productOptionCode;
    private String basketCode;
    private Short quantity;
    private Boolean isChecked;

}
