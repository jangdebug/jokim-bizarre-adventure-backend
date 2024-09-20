package com.jokim.sivillage.api.product.dto.out;

import com.jokim.sivillage.api.brand.domain.Brand;

public class FilteredProductDto {

    private Long productId;
    private String productName;
    private String detail;
    private String imageUrl;
    private Double price;
    private Integer discountRate;
    private Brand brandName;
    private Boolean productWishListId;

}
