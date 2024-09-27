package com.jokim.sivillage.api.product.infrastructure;

import com.jokim.sivillage.api.product.dto.out.ProductListResponseDto;
import com.jokim.sivillage.api.product.dto.out.ProductResponseDto;
import com.jokim.sivillage.api.product.dto.out.option.ProductOptionResponseDto;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepositoryCustom {


    ProductResponseDto findProductDtoByProductCode(String productCode);

    ProductListResponseDto getProductListByProductCode(String productCode);

    List<ProductListResponseDto> getMostDiscountProduct(Integer count);

    List<ProductOptionResponseDto> getProductOptionListByProductCode(String productCode);

}
