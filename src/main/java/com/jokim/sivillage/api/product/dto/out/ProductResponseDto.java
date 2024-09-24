package com.jokim.sivillage.api.product.dto.out;

import com.jokim.sivillage.api.product.domain.Product;
import com.jokim.sivillage.api.product.vo.out.ProductResponseVo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;


@ToString
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductResponseDto {

    private String productCode;
    private String productName;
    private Integer discountRate;
    private Double amount;
    private Double price;
    private String detail;
    private String brandCode;

    public ProductResponseVo toResponseVo() {
        return ProductResponseVo.builder()
            .productCode(productCode)
            .productName(productName)
            .discountRate(discountRate)
            .amount(amount)
            .price(price)
            .detail(detail)
            .brandCode(brandCode)
            .build();
    }

    public static ProductResponseDto of(Product product) {
        Integer discountRate = (int) Math.round(
            ((1 - product.getDiscountPrice() / product.getStandardPrice()) * 100));
        return ProductResponseDto.builder()
            .productCode(product.getProductCode())
            .productName(product.getProductName())
            .discountRate(discountRate)
            .amount(product.getStandardPrice())
            .price(product.getDiscountPrice())
            .detail(product.getDetail())
            .brandCode(product.getBrandCode())
            .build();
    }

//    public void onSearchChangeBrandName(String brandName) {
//        this.brandName = brandName;
//    }
}
