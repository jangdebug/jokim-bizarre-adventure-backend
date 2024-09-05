package com.jokim.sivillage.api.product.domain;

import jakarta.persistence.Id;

public class ProductCategoryList {

    @Id
    private Long productCategoryListId;

    private Product product;


    private String categoryDepth0Id;


    private String categoryDepth1Id;

    private String categoryDepth2Id;

    private String categoryDepth3Id;

}
