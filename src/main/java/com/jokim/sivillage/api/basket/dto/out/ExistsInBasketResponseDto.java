package com.jokim.sivillage.api.basket.dto.out;

import com.jokim.sivillage.api.basket.vo.out.GetExistsInBasketResponseVo;
import lombok.Builder;

@Builder
public class ExistsInBasketResponseDto {

    private Boolean isInBasket;

    public static ExistsInBasketResponseDto toDto(Boolean isInBasket) {
        return ExistsInBasketResponseDto
            .builder()
            .isInBasket(isInBasket)
            .build();
    }

    public GetExistsInBasketResponseVo toVo() {
        return GetExistsInBasketResponseVo
            .builder()
            .isInBasket(isInBasket)
            .build();
    }

}
