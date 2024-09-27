package com.jokim.sivillage.api.bridge.productmedialist.application;

import com.jokim.sivillage.api.bridge.productmedialist.dto.in.ProductMediaListRequestDto;
import com.jokim.sivillage.api.bridge.productmedialist.dto.out.AllProductMediaListsResponseDto;

import com.jokim.sivillage.api.bridge.productmedialist.dto.out.ThumbnailProductMediaListResponseDto;
import java.util.List;
import java.util.Optional;

public interface ProductMediaListService {

    void addProductMediaList(ProductMediaListRequestDto productMediaListRequestDto);

    List<AllProductMediaListsResponseDto> getAllProductMediaLists(String productCode);
    ThumbnailProductMediaListResponseDto getThumbnailByProductCode(String productCode);

    void updateProductMediaList(ProductMediaListRequestDto productMediaListRequestDto);

}
