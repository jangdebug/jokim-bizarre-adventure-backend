package com.jokim.sivillage.api.basket.application;

import com.jokim.sivillage.api.basket.dto.in.AddBasketRequestDto;
import com.jokim.sivillage.api.basket.dto.in.DeleteBasketItemRequestDto;
import com.jokim.sivillage.api.basket.dto.in.UpdateBasketRequestDto;
import com.jokim.sivillage.api.basket.dto.out.AllBasketItemsResponseDto;
import com.jokim.sivillage.api.basket.dto.out.BasketItemCountResponseDto;
import com.jokim.sivillage.api.basket.dto.out.ExistsInBasketResponseDto;
import com.jokim.sivillage.api.basket.dto.out.ProductOptionInfoResponseDto;

import java.util.List;

public interface BasketService {

    void addToBasket(AddBasketRequestDto addBasketRequestDto);

    List<AllBasketItemsResponseDto> getAllBasketItems(String accessToken);
    BasketItemCountResponseDto getBasketItemCount(String accessToken);
    ExistsInBasketResponseDto existsInBasket(String accessToken, String productOptionCode);
    ProductOptionInfoResponseDto getProductOptionInfo(String productOptionCode);

    void updateBasketItemQuantity(UpdateBasketRequestDto updateBasketRequestDto);
    void updateBasketItemCheck(UpdateBasketRequestDto updateBasketRequestDto);

    void deleteBasketItems(List<DeleteBasketItemRequestDto> deleteBasketItemRequestDtoList);

}
