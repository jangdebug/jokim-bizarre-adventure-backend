package com.jokim.sivillage.api.basket.dto.in;

import com.jokim.sivillage.api.basket.domain.Basket;
import com.jokim.sivillage.api.basket.domain.BasketState;
import com.jokim.sivillage.api.basket.vo.in.AddToBasketRequestVo;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AddBasketRequestDto {

    private String accessToken;
    private String productCode;
    private String productOptionCode;
    private Short quantity;

    public static AddBasketRequestDto toDto(AddToBasketRequestVo addToBasketRequestVo,
                                            String accessToken) {

        return AddBasketRequestDto.builder()
            .accessToken(accessToken)
            .productCode(addToBasketRequestVo.getProductCode())
            .productOptionCode(addToBasketRequestVo.getProductOptionCode())
            .quantity(addToBasketRequestVo.getQuantity())
            .build();

    }

    public Basket toEntity(Long id, String uuid, String basketCode, Boolean isChecked,
        String basketState) {

        return Basket.builder()
            .id(id)
            .uuid(uuid)
            .productCode(productCode)
            .productOptionCode(productOptionCode)
            .basketCode(basketCode)
            .quantity(quantity)
            .isChecked(isChecked)
            .basketState(BasketState.valueOf(basketState))
            .build();
    }

}
