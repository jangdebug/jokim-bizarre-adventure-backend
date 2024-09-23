package com.jokim.sivillage.api.product.application;

import com.jokim.sivillage.api.product.dto.in.ProductRequestDto;
import com.jokim.sivillage.api.product.dto.out.ProductListResponseDto;
import com.jokim.sivillage.api.product.dto.out.ProductResponseDto;
import java.util.List;


public interface ProductService {


    ProductResponseDto getProductByProductCode(String productCode);

    void saveProduct(ProductRequestDto productRequestDto);

    void updateProduct(ProductRequestDto productRequestDto);

    void deleteProduct(String productCode);

    // media 부분에 구현되어 있어서 보류
//    List<ProductImageResponseDto> getProductImageByProductCode(String productCode);

    List<ProductListResponseDto> getRandomProducts(Integer count);


    ProductListResponseDto getProductListByProductCode(
        String productCodeList);

    // product-category에 구현되어 있음
//    List<ProductListResponseDto> getProductListByOptions(Long sizeId,
//        Long colorId, Long etcId);

    List<ProductListResponseDto> getMostDiscountProduct(Integer count);

}


