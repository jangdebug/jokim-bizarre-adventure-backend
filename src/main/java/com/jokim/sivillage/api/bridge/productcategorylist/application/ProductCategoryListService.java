package com.jokim.sivillage.api.bridge.productcategorylist.application;

import com.jokim.sivillage.api.bridge.productcategorylist.dto.ProductCategoryListRequestDto;
import com.jokim.sivillage.api.bridge.productcategorylist.dto.ProductCategoryListResponseDto;
import com.jokim.sivillage.common.utils.CursorPage;

public interface ProductCategoryListService {

    void addProductByCategories(ProductCategoryListRequestDto productCategoryListRequestDto);

    CursorPage<ProductCategoryListResponseDto> getProductCategoryListByCategories(
        String categoryCode, Long lastId, Integer pageSize, Integer pageNo);

    CursorPage<ProductCategoryListResponseDto> getProductCodesByOptions(
        String mainCategoryCode, String secondaryCategoryCode,
        String tertiaryCategoryCode, String quaternaryCategoryCode,
        Long lastId, Integer pageSize, Integer pageNo,
        Long sizeId, Long colorId, Long etcId);

    void updateProductCategoryList(ProductCategoryListRequestDto productCategoryListRequestDto);

}
