package com.jokim.sivillage.api.bridge.productcategorylist.dto;

import com.jokim.sivillage.api.bridge.productcategorylist.domain.ProductCategoryList;
import com.jokim.sivillage.api.bridge.productcategorylist.vo.ProductCategoryListRequestVo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductCategoryListRequestDto {

    private String productCode;
    private String mainCategoryCode;
    private String secondaryCategoryCode;
    private String tertiaryCategoryCode;
    private String quaternaryCategoryCode;
    private Boolean isOnSale;

    public static ProductCategoryListRequestDto toDto(
        ProductCategoryListRequestVo productCategoryListRequestVo) {
        return ProductCategoryListRequestDto.builder()
                .productCode(productCategoryListRequestVo.getProductCode())
                .mainCategoryCode(productCategoryListRequestVo.getMainCategoryCode())
                .secondaryCategoryCode(productCategoryListRequestVo.getSecondaryCategoryCode())
                .tertiaryCategoryCode(productCategoryListRequestVo.getTertiaryCategoryCode())
                .quaternaryCategoryCode(productCategoryListRequestVo.getQuaternaryCategoryCode())
                .isOnSale(productCategoryListRequestVo.getIsOnSale())
                .build();
    }

    public ProductCategoryList toEntity(Boolean isOnSale) {     // add product-category-list
        return ProductCategoryList.builder()
                .productCode(productCode)
                .mainCategoryCode(mainCategoryCode)
                .secondaryCategoryCode(secondaryCategoryCode)
                .tertiaryCategoryCode(tertiaryCategoryCode)
                .quaternaryCategoryCode(quaternaryCategoryCode)
                .isOnSale(isOnSale)
                .build();
    }

    public ProductCategoryList toEntity(Long id) {     // update product-category-list
        return ProductCategoryList.builder()
            .id(id)
            .productCode(productCode)
            .mainCategoryCode(mainCategoryCode)
            .secondaryCategoryCode(secondaryCategoryCode)
            .tertiaryCategoryCode(tertiaryCategoryCode)
            .quaternaryCategoryCode(quaternaryCategoryCode)
            .isOnSale(isOnSale)
            .build();
    }

}
