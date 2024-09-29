package com.jokim.sivillage.api.basket.dto.in;

import com.jokim.sivillage.api.basket.domain.Basket;
import com.jokim.sivillage.api.basket.domain.BasketState;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class DeleteBasketItemRequestDto {

    private String basketCode;

    public static DeleteBasketItemRequestDto toDto(String basketCode) {
        return DeleteBasketItemRequestDto.builder()
                .basketCode(basketCode)
                .build();
    }

    public Basket toEntity(Basket basket) {
        return Basket.builder()
                .id(basket.getId())
                .uuid(basket.getUuid())
                .productCode(basket.getProductCode())
                .productOptionCode(basket.getProductOptionCode())
                .basketCode(basketCode)
                .quantity(basket.getQuantity())
                .isChecked(false)
                .basketState(BasketState.REMOVED)
                .build();

    }

}