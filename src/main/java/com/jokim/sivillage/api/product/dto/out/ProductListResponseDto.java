package com.jokim.sivillage.api.product.dto.out;


import com.jokim.sivillage.api.product.vo.out.ProductListResponseVo;
import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
public class ProductListResponseDto {

    private String productCode;
    private String productName;
    private String imageUrl;
    private String discountRate;
    private String brandName;


    public ProductListResponseVo toResponseVo() {
        return ProductListResponseVo.builder()
            .productCode(productCode)
            .productName(productName)
            .imageUrl(imageUrl)
            .discountRate(discountRate)
            .brandName(brandName)
            .build();
    }

}
