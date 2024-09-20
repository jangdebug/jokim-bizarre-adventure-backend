package com.jokim.sivillage.api.product.dto.in;

import com.jokim.sivillage.api.product.domain.Product;
import lombok.Getter;

@Getter
public class ProductRequestDto {

    private Long id;
    private String productName;
    private boolean isOnSale;
    private String detail;
    private Double price;

    public Product toEntity() {
        return Product.builder().
            id(id).
            productName(productName).
            isOnSale(isOnSale).
            detail(detail).
            price(price).
            build();
    }


}
