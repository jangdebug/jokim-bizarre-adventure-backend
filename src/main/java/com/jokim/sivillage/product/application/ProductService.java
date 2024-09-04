package com.jokim.sivillage.product.application;

import com.jokim.sivillage.product.dto.out.DailyHotProductResponseDto;
import com.jokim.sivillage.product.dto.out.ProductResponseDto;

import java.util.List;


public interface ProductService {

    ProductResponseDto getProductById(long id);

    List<DailyHotProductResponseDto> getDailyHotProducts();

    List<ProductResponseDto> getFilteredProducts(Long sizeId, Long colorId, Long etcId);

    List<ProductResponseDto> getRandomProducts(Integer count);

//    List<ProductResponseDto> get
}

