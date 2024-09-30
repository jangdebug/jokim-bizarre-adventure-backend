package com.jokim.sivillage.api.trending.mostViewProduct.application;

import com.jokim.sivillage.api.trending.mostViewProduct.dto.MostViewProductDto;
import java.util.List;

public interface MostViewProductService {
    List<MostViewProductDto> getMostViewProduct();
}
