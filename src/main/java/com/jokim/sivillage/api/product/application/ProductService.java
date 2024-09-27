package com.jokim.sivillage.api.product.application;

import com.jokim.sivillage.api.product.dto.in.ProductRequestDto;
import com.jokim.sivillage.api.product.dto.out.ProductListResponseDto;
import com.jokim.sivillage.api.product.dto.out.ProductResponseDto;
import com.jokim.sivillage.api.product.dto.out.option.ProductOptionResponseDto;
import java.util.List;


public interface ProductService {


    ProductResponseDto getProductByProductCode(String productCode);

    void saveProduct(ProductRequestDto productRequestDto);

    void updateProduct(ProductRequestDto productRequestDto);

    void deleteProduct(String productCode);


    List<ProductListResponseDto> getRandomProducts(Integer count);


    ProductListResponseDto getProductListByProductCode(
        String productCodeList);

    List<ProductListResponseDto> getMostDiscountProduct(Integer count);

    List<ProductOptionResponseDto> getProductOptionByProductCode(String productCode);
}


