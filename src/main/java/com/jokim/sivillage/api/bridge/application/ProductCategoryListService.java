package com.jokim.sivillage.api.bridge.application;

import com.jokim.sivillage.api.bridge.dto.ProductCategoryListRequestDto;

public interface ProductCategoryListService {

    void addProductByCategories(ProductCategoryListRequestDto productCategoryListRequestDto);

}
