package com.jokim.sivillage.api.mostViewProduct.dto;

import com.jokim.sivillage.api.brand.domain.Brand;
import com.jokim.sivillage.api.mostViewProduct.domain.MostViewProduct;
import com.jokim.sivillage.api.mostViewProduct.vo.MostViewProductVo;
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
public class MostViewProductDto {
    private String productCode;
    private Integer rankValue;
    private String productName;
    private Double discountPrice;
    private Integer discountRate;
    private String brandCode;
    private String englishName;
    private LocalDateTime updateAt;

    public static MostViewProductDto toDto(
        MostViewProduct mostViewProduct, Product product, Brand brand, Integer discountRate) {
        return MostViewProductDto.builder()
            .productCode(mostViewProduct.getProductCode())
            .rankValue(mostViewProduct.getRankValue())
            .updateAt(mostViewProduct.getUpdateAt())
            .productName(product.getProductName())
            .discountPrice(product.getDiscountPrice())
            .discountRate(discountRate)
            .brandCode(product.getBrandCode())
            .englishName(brand.getEnglishName())
            .build();
    }

    public MostViewProductVo toVo(){
        return MostViewProductVo.builder()
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
