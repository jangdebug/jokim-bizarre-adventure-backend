package com.jokim.sivillage.api.product.dto.out;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ProductDto {

    private String productCode;
    private String productName;
    private String detail;
    private Double standardPrice;
    private Double discountPrice;

}
