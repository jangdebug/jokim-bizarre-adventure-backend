package com.jokim.sivillage.api.product.dto.out;


import com.jokim.sivillage.api.product.vo.out.ProductListResponseVo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ProductListResponseDto {

    private String productCode;
    private String productName;
    private Double price;
    private Integer discountRate;
    private String brandCode;


    public ProductListResponseVo toResponseVo() {
        return ProductListResponseVo.builder()
            .productCode(productCode)
            .productName(productName)
            .price(price)
            .discountRate(discountRate)
            .brandCode(brandCode)
            .build();
    }

}
