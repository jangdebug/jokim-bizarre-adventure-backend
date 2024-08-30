package com.jokim.sivillage.product.application;

import com.jokim.sivillage.product.dto.out.DailyHotProductResponseDto;
import com.jokim.sivillage.product.dto.out.ProductResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ProductService {

    ProductResponseDto getProductById(long id);
    List<DailyHotProductResponseDto> getDailyHotProduct();

}

