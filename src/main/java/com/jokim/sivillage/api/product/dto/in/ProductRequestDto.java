package com.jokim.sivillage.api.product.dto.in;

import com.jokim.sivillage.api.brand.Brand;
import com.jokim.sivillage.api.product.domain.Product;
import lombok.Getter;

@Getter
public class ProductRequestDto {

    private Long id;
    private Brand brand;
    private String productName;
    private boolean isOnSale;
    private String detail;
    private Double price;

    public Product toEntity() {
        return Product.builder().
            id(id).
            brand(brand).
            productName(productName).
            isOnSale(isOnSale).
            detail(detail).
            price(price).
            build();
    }


}
