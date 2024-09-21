package com.jokim.sivillage.api.bridge.productcategorylist.dto;

import com.jokim.sivillage.api.bridge.productcategorylist.domain.ProductCategoryList;
import com.jokim.sivillage.api.bridge.productcategorylist.vo.AddProductCategoryListRequestVo;
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

    public static ProductCategoryListRequestDto toDto(AddProductCategoryListRequestVo addProductCategoryListRequestVo) {
        return ProductCategoryListRequestDto.builder()
                .productCode(addProductCategoryListRequestVo.getProductCode())
                .mainCategoryCode(addProductCategoryListRequestVo.getMainCategoryCode())
                .secondaryCategoryCode(addProductCategoryListRequestVo.getSecondaryCategoryCode())
                .tertiaryCategoryCode(addProductCategoryListRequestVo.getTertiaryCategoryCode())
                .quaternaryCategoryCode(addProductCategoryListRequestVo.getQuaternaryCategoryCode())
                .isOnSale(addProductCategoryListRequestVo.getIsOnSale())
                .build();
    }

    public ProductCategoryList toEntity(Boolean isOnSale) {
        return ProductCategoryList.builder()
                .productCode(productCode)
                .mainCategoryCode(mainCategoryCode)
                .secondaryCategoryCode(secondaryCategoryCode)
                .tertiaryCategoryCode(tertiaryCategoryCode)
                .quaternaryCategoryCode(quaternaryCategoryCode)
                .isOnSale(isOnSale)
                .build();
    }

}
