package com.jokim.sivillage.api.basket.infrastructure;

import com.jokim.sivillage.api.basket.dto.out.ProductOptionInfoResponseDto;
import com.jokim.sivillage.api.product.domain.ProductOption;

public interface BasketRepositoryCustom {

    ProductOptionInfoResponseDto getProductOptionInfo(ProductOption productOption);

}
