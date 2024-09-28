package com.jokim.sivillage.api.product.vo.out;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProductOptionResponseVo {
    private String productOptionCode;
    private String sizeValues;
    private String colorValues;
    private Integer stock;
}
