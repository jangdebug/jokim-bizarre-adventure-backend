package com.jokim.sivillage.api.bridge.application;

import com.jokim.sivillage.api.bridge.dto.in.ProductMediaListRequestDto;
import com.jokim.sivillage.api.bridge.dto.out.ProductMediaListResponseDto;

import java.util.List;

public interface ProductMediaListService {

    void addProductMediaList(ProductMediaListRequestDto productMediaListRequestDto);

    List<ProductMediaListResponseDto> getProductMediaList(String productCode);

    void updateProductMediaList(ProductMediaListRequestDto productMediaListRequestDto);

}
