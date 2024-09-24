package com.jokim.sivillage.api.product.vo.out;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductResponseVo {

    private String productCode;
    private String productName;
    private Integer discountRate;
    private Double amount;
    private Double price;
    private String detail;
    private String brandCode;

}
