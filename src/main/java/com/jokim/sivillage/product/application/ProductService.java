package com.jokim.sivillage.product.application;

import com.jokim.sivillage.product.domain.Product;
import com.jokim.sivillage.product.dto.out.DailyHotProductResponseDto;
import com.jokim.sivillage.product.dto.out.FilteredProductDto;
import com.jokim.sivillage.product.dto.out.ProductResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ProductService {

    ProductResponseDto getProductById(long id);

    List<DailyHotProductResponseDto> getDailyHotProduct();

    List<ProductResponseDto> getFilteredProducts(Long sizeId, Long colorId, Long etcId);

    List<ProductResponseDto> getRandomProducts(Integer count);
}

