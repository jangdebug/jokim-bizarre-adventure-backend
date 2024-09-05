package com.jokim.sivillage.api.product.application;

import com.jokim.sivillage.api.product.dto.in.ProductRequestDto;
import com.jokim.sivillage.api.product.dto.out.DailyHotProductResponseDto;
import com.jokim.sivillage.api.product.dto.out.ProductResponseDto;
import java.util.List;


public interface ProductService {

    ProductResponseDto getProductById(long id);

    void saveProduct(ProductRequestDto productRequestDto);

    ProductResponseDto updateProduct(Long id, ProductRequestDto productRequestDto);

//    void deleteProduct(Long id);

    List<DailyHotProductResponseDto> getDailyHotProducts();

    List<ProductResponseDto> getFilteredProducts(Long sizeId, Long colorId, Long etcId);

    List<ProductResponseDto> getRandomProducts(Integer count);

    List<ProductResponseDto> getProductsBySortType(String sortType);
}


