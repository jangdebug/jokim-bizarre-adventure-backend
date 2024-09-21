package com.jokim.sivillage.api.product.infrastructure;

import com.jokim.sivillage.api.product.domain.Product;
import com.jokim.sivillage.api.product.dto.out.ProductImageResponseDto;
import com.jokim.sivillage.api.product.dto.out.ProductListResponseDto;
import com.jokim.sivillage.api.product.dto.out.ProductResponseDto;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepositoryCustom {

    List<Product> findFilteredProduct(Long sizeId, Long colorId, Long etcId);

    ProductResponseDto findProductDtoByProductCode(String productCode);

    List<ProductListResponseDto> getRandomProducts(Integer count);

    List<ProductImageResponseDto> getProductImagesByProductCode(String productCode);

    List<ProductListResponseDto> getProductListByProductCodeList(List<String> productCodeList);
}
