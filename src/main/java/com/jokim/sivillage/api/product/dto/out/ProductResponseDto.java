package com.jokim.sivillage.api.product.dto.out;

import com.jokim.sivillage.api.brand.Brand;
import com.jokim.sivillage.api.product.vo.out.ProductResponseVo;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@ToString
@Getter
public class ProductResponseDto {

    private Long productId;
    private Brand brand;
    private String productName;
    private boolean isOnSale;
    private String detail;
    private Double price;

    public ProductResponseVo toResponseVo() {
        return ProductResponseVo.builder()
            .id(productId)
            .brandName(brand.getMainName())
            .productName(productName) // TODO .discountRate() 필요없을 시
            .detail(detail)
            .price(price)
            .build();
    }


}
