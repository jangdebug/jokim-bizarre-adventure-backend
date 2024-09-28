package com.jokim.sivillage.api.basket.dto.in;

import com.jokim.sivillage.api.basket.domain.Basket;
import com.jokim.sivillage.api.basket.domain.BasketState;
import com.jokim.sivillage.api.basket.vo.in.UpdateBasketItemRequestVo;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UpdateBasketRequestDto {

    private String accessToken;
    private String basketCode;
    private Short quantity;

    public static UpdateBasketRequestDto toDto(String accessToken, UpdateBasketItemRequestVo updateBasketItemRequestVo) {

            return UpdateBasketRequestDto.builder()
                    .accessToken(accessToken)
                    .basketCode(updateBasketItemRequestVo.getBasketCode())
                    .quantity(updateBasketItemRequestVo.getQuantity())
                    .build();
    }

    public Basket toEntity(Basket basket) {
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

}
