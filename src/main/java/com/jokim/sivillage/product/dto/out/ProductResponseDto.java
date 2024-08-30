package com.jokim.sivillage.product.dto.out;

import com.jokim.sivillage.brand.domain.Brand;
import com.jokim.sivillage.product.vo.out.ProductResponseVo;
import lombok.Setter;
import lombok.ToString;

@Setter
@ToString
public class ProductResponseDto {

    private Long productId;
    private Brand brand;
    private String productName;
    private boolean isOnSale;
    private String detail;
    private Double price;

    public ProductResponseVo toResponseVo(){
        return ProductResponseVo.builder()
                .productId(productId)
                .brandName(brand.getMainName())
                .productName(productName) // TODO .discountRate() 필요없을 시
                .detail(detail)
                .price(price)
                .build();
    }


}
