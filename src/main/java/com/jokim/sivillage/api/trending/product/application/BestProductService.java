package com.jokim.sivillage.api.trending.product.application;

import com.jokim.sivillage.api.trending.product.dto.BestProductResponseDto;
import java.util.List;

public interface BestProductService {
    List<BestProductResponseDto> getBestProduct();
}
