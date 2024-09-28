package com.jokim.sivillage.api.product.dto.out.option;

import com.jokim.sivillage.api.product.vo.out.ProductOptionResponseVo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductOptionResponseDto {
    private String productOptionCode;
    private String sizeValue;
    private String colorValue;
    private Integer stock;

    public ProductOptionResponseVo toResponseVo() {
        return ProductOptionResponseVo.builder()
            .productOptionCode(productOptionCode)
            .sizeValues(sizeValue)
            .colorValues(colorValue)
            .stock(stock)
            .build();
    }
}
