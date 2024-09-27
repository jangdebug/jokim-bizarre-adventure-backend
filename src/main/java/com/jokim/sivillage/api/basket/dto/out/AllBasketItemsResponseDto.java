package com.jokim.sivillage.api.basket.dto.out;

import com.jokim.sivillage.api.basket.domain.Basket;
import com.jokim.sivillage.api.basket.vo.out.GetAllBasketItemsResponseVo;
import lombok.Builder;

@Builder
public class AllBasketItemsResponseDto {

    private String productCode;
    private String productOptionCode;
    private String basketCode;
    private Short quantity;
    private Boolean isChecked;

    public static AllBasketItemsResponseDto toDto(Basket basket) {
        return AllBasketItemsResponseDto.builder()
            .productCode(basket.getProductCode())
            .productOptionCode(basket.getProductOptionCode())
            .basketCode(basket.getBasketCode())
            .quantity(basket.getQuantity())
            .isChecked(basket.getIsChecked())
            .build();
    }

    public GetAllBasketItemsResponseVo toVo() {
        return GetAllBasketItemsResponseVo.builder()
            .productCode(productCode)
            .productOptionCode(productOptionCode)
            .basketCode(basketCode)
            .quantity(quantity)
            .isChecked(isChecked)
            .build();
    }

}
