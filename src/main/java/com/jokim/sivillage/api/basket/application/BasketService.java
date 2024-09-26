package com.jokim.sivillage.api.basket.application;

import com.jokim.sivillage.api.basket.dto.BasketRequestDto;

public interface BasketService {

    void addToBasket(BasketRequestDto basketRequestDto);

}
