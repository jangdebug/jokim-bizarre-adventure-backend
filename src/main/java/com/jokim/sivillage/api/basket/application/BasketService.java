package com.jokim.sivillage.api.basket.application;

import com.jokim.sivillage.api.basket.dto.in.BasketRequestDto;
import com.jokim.sivillage.api.basket.dto.out.AllBasketItemsResponseDto;
import com.jokim.sivillage.api.basket.dto.out.BasketItemCountResponseDto;
import java.util.List;

public interface BasketService {

    void addToBasket(BasketRequestDto basketRequestDto);

    List<AllBasketItemsResponseDto> getAllBasketItems(String accessToken);
    BasketItemCountResponseDto getBasketItemCount(String accessToken);

}
