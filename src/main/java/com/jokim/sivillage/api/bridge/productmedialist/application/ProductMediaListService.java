package com.jokim.sivillage.api.bridge.productmedialist.application;

import com.jokim.sivillage.api.bridge.productmedialist.dto.in.ProductMediaListRequestDto;
import com.jokim.sivillage.api.bridge.productmedialist.dto.out.ProductMediaListResponseDto;

import java.util.List;

public interface ProductMediaListService {

    void addProductMediaList(ProductMediaListRequestDto productMediaListRequestDto);

    List<ProductMediaListResponseDto> getProductMediaList(String productCode);

    void updateProductMediaList(ProductMediaListRequestDto productMediaListRequestDto);

}
