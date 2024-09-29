package com.jokim.sivillage.api.basket.dto.in;

import com.jokim.sivillage.api.basket.domain.Basket;
import com.jokim.sivillage.api.basket.domain.BasketState;
import com.jokim.sivillage.api.basket.vo.in.UpdateBasketItemCheckRequestVo;
import com.jokim.sivillage.api.basket.vo.in.UpdateBasketItemQuantityRequestVo;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UpdateBasketRequestDto {

    private String accessToken;
    private String basketCode;
    private Boolean isChecked;
    private Short quantity;

    public static UpdateBasketRequestDto toDto(String accessToken, UpdateBasketItemQuantityRequestVo updateBasketItemQuantityRequestVo) {

            return UpdateBasketRequestDto.builder()
                    .accessToken(accessToken)
                    .basketCode(updateBasketItemQuantityRequestVo.getBasketCode())
                    .quantity(updateBasketItemQuantityRequestVo.getQuantity())
                    .build();
    }

    public static UpdateBasketRequestDto toDto(UpdateBasketItemCheckRequestVo updateBasketItemCheckRequestVo) {
        return UpdateBasketRequestDto.builder()
                .basketCode(updateBasketItemCheckRequestVo.getBasketCode())
                .isChecked(updateBasketItemCheckRequestVo.getIsChecked())
                .build();
    }

    public Basket toEntityForQuantity(Basket basket) {     // update quantity
            return Basket.builder()
                    .id(basket.getId())
                    .uuid(basket.getUuid())
                    .productCode(basket.getProductCode())
                    .productOptionCode(basket.getProductOptionCode())
                    .basketCode(basketCode)
                    .quantity(quantity)
                    .isChecked(true)
                    .basketState(BasketState.ACTIVE)
                    .build();
    }

    public Basket toEntityForCheck(Basket basket) {     // update isChecked
        return Basket.builder()
                .id(basket.getId())
                .uuid(basket.getUuid())
                .productCode(basket.getProductCode())
                .productOptionCode(basket.getProductOptionCode())
                .basketCode(basketCode)
                .quantity(basket.getQuantity())
                .isChecked(isChecked)
                .basketState(BasketState.ACTIVE)
                .build();
    }

}
