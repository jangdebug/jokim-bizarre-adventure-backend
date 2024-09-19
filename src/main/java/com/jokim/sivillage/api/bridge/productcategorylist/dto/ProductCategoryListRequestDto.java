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
    private String mainCategoyCode;
    private String secondaryCategoyCode;
    private String tertiaryCategoyCode;
    private String quaternaryCategoyCode;

    public static ProductCategoryListRequestDto toDto(AddProductCategoryListRequestVo addProductCategoryListRequestVo) {
        return ProductCategoryListRequestDto.builder()
                .productCode(addProductCategoryListRequestVo.getProductCode())
                .mainCategoyCode(addProductCategoryListRequestVo.getMainCategoyCode())
                .secondaryCategoyCode(addProductCategoryListRequestVo.getSecondaryCategoyCode())
                .tertiaryCategoyCode(addProductCategoryListRequestVo.getTertiaryCategoyCode())
                .quaternaryCategoyCode(addProductCategoryListRequestVo.getQuaternaryCategoyCode())
                .build();
    }

    public ProductCategoryList toEntity() {
        return ProductCategoryList.builder()
                .productCode(productCode)
                .mainCategoryCode(mainCategoyCode)
                .secondaryCategoyCode(secondaryCategoyCode)
                .tertiaryCategoyCode(tertiaryCategoyCode)
                .quaternaryCategoyCode(quaternaryCategoyCode)
                .build();
    }

}
