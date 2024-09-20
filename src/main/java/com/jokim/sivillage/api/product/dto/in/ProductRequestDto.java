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
    private boolean isOnSale;
    private String detail;
    private Double standardPrice;
    private Double discountPrice;

    public Product toEntity() {
        return Product.builder().
            productCode(productCode).
            productName(productName).
            isOnSale(isOnSale).
            detail(detail).
            standardPrice(standardPrice).
            discountPrice(discountPrice).
            build();
    }

    public Product toEntity(Long productId) {
        return Product.builder().
            id(productId).
            productCode(productCode).
            productName(productName).
            isOnSale(isOnSale).
            detail(detail).
            standardPrice(standardPrice).
            discountPrice(discountPrice).
            build();
    }


    public static ProductRequestDto toDto(ProductRequestVo productRequestVo) {
        return ProductRequestDto.builder()
            .productCode(productRequestVo.getProductCode())
            .productName(productRequestVo.getProductName())
            .isOnSale(productRequestVo.isOnSale())
            .detail(productRequestVo.getDetail())
            .standardPrice(productRequestVo.getStandardPrice())
            .discountPrice(productRequestVo.getDiscountPrice())
            .build();
    }


}
