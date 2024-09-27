package com.jokim.sivillage.api.basket.dto.out;

import com.jokim.sivillage.api.basket.vo.out.GetBasketItemCountResponseVo;
import lombok.Builder;

@Builder
public class BasketItemCountResponseDto {

    private Integer count;

    public static BasketItemCountResponseDto toDto(Integer count) {
        return BasketItemCountResponseDto.builder()
            .count(count)
            .build();
    }

    public GetBasketItemCountResponseVo toVo() {
        return GetBasketItemCountResponseVo
            .builder()
            .count(count)
            .build();
    }

}
