package com.jokim.sivillage.api.product.infrastructure;

import com.jokim.sivillage.api.product.dto.out.ProductListResponseDto;
import com.jokim.sivillage.api.product.dto.out.ProductResponseDto;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepositoryCustom {


    ProductResponseDto findProductDtoByProductCode(String productCode);

    List<ProductListResponseDto> getRandomProducts(Integer count);

    // media 부분에 구현되어 있어서 보류
//    List<ProductImageResponseDto> getProductImagesByProductCode(String productCode);

    ProductListResponseDto getProductListByProductCode(String productCode);

    List<ProductListResponseDto> getProductListByOpions(Long sizeId,
        Long colorId, Long etcId);

    List<ProductListResponseDto> getMostDiscountProduct(Integer count);
}
