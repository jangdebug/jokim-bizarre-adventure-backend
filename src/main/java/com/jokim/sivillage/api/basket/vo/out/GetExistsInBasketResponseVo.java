package com.jokim.sivillage.api.basket.vo.out;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GetExistsInBasketResponseVo {

    private Boolean isInBasket;

}
