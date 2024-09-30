package com.jokim.sivillage.api.best.product.application;

import com.jokim.sivillage.api.best.product.dto.BestProductResponseDto;
import java.util.List;

public interface BestProductService {
    List<BestProductResponseDto> getBestProduct();
}
