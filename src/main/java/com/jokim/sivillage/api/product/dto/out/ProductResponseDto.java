package com.jokim.sivillage.api.product.dto.out;

import com.jokim.sivillage.api.product.vo.out.ProductResponseVo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;


@ToString
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductResponseDto {

    private String productCode;
    private String productName;
    private Integer discountRate;
    private Double amount;
    private Double price;
    private String detail;


    public ProductResponseVo toResponseVo() {
        return ProductResponseVo.builder()
            .productCode(productCode)
            .productName(productName)
            .discountRate(discountRate)
            .amount(amount)
            .price(price)
            .detail(detail)
            .build();
    }

//    public void onSearchChangeBrandName(String brandName) {
//        this.brandName = brandName;
//    }
}
