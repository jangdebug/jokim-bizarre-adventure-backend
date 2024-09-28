package com.jokim.sivillage.api.best.product.dto;

import com.jokim.sivillage.api.best.product.domain.BestProduct;
import com.jokim.sivillage.api.best.product.vo.BestProductResponseVo;
import com.jokim.sivillage.api.brand.domain.Brand;
import com.jokim.sivillage.api.product.domain.Product;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BestProductResponseDto {
    private String productCode;
    private Integer rankValue;
    private String productName;
    private Double discountPrice;
    private Integer discountRate;
    private String brandCode;
    private String englishName;
    private LocalDateTime updateAt;

    public static BestProductResponseDto toDto(BestProduct bestProduct, Product product, Brand brand, Integer discountRate) {
        return BestProductResponseDto.builder()
            .productCode(bestProduct.getProductCode())
            .rankValue(bestProduct.getRankValue())
            .updateAt(bestProduct.getUpdateAt())
            .productName(product.getProductName())
            .discountPrice(product.getDiscountPrice())
            .discountRate(discountRate)
            .brandCode(product.getBrandCode())
            .englishName(brand.getEnglishName())
            .build();
    }

    public BestProductResponseVo toVo(){
        return BestProductResponseVo.builder()
            .productCode(productCode)
            .rankValue(rankValue)
            .updateAt(updateAt)
            .productName(productName)
            .discountPrice(discountPrice)
            .discountRate(discountRate)
            .brandCode(brandCode)
            .englishName(englishName)
            .build();
    }
}
