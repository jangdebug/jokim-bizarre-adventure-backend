package com.jokim.sivillage.api.mostViewProduct.application;

import com.jokim.sivillage.api.mostViewProduct.dto.MostViewProductDto;
import java.util.List;

public interface MostViewProductService {
    List<MostViewProductDto> getMostViewProduct();
}
