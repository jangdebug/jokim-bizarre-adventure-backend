package com.jokim.sivillage.api.basket.dto.out;

import com.jokim.sivillage.api.basket.vo.out.GetProductOptionInfoResponseVo;
import lombok.Builder;

@Builder
public class ProductOptionInfoResponseDto {

    private String optionInfo;

    public static ProductOptionInfoResponseDto toDto(String productOptionInfo) {
        return ProductOptionInfoResponseDto.builder()
                .optionInfo(productOptionInfo)
                .build();
    }

    public GetProductOptionInfoResponseVo toVo() {
        return GetProductOptionInfoResponseVo.builder()
                .optionInfo(optionInfo)
                .build();
    }

}
