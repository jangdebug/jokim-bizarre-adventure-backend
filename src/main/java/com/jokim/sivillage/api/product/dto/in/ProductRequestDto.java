package com.jokim.sivillage.api.product.dto.in;

import com.jokim.sivillage.api.product.domain.Product;
import com.jokim.sivillage.api.product.vo.in.ProductRequestVo;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@Builder
public class ProductRequestDto {

    private String productCode;
    private String productName;
    private String detail;
    private Double standardPrice;
    private Double discountPrice;
    private String brandCode;

    public Product toEntity() {
        return Product.builder().
            productCode(productCode).
            productName(productName).
            detail(detail).
            standardPrice(standardPrice).
            discountPrice(discountPrice).
            brandCode(brandCode).
            build();
    }

    public Product toEntity(Long productId) {
        return Product.builder().
            id(productId).
            productCode(productCode).
            productName(productName).
            detail(detail).
            standardPrice(standardPrice).
            discountPrice(discountPrice).
            brandCode(brandCode).
            build();
    }


    public static ProductRequestDto toDto(ProductRequestVo productRequestVo) {
        return ProductRequestDto.builder()
            .productCode(productRequestVo.getProductCode())
            .productName(productRequestVo.getProductName())
            .detail(productRequestVo.getDetail())
            .standardPrice(productRequestVo.getStandardPrice())
            .discountPrice(productRequestVo.getDiscountPrice())
            .brandCode(productRequestVo.getBrandCode())
            .build();
    }


}
