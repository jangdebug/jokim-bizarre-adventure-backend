package com.jokim.sivillage.api.bridge.productcategorylist.dto;

import com.jokim.sivillage.api.bridge.productcategorylist.vo.GetProductCategoryListResponseVo;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProductCategoryListResponseDto {

    private String productCode;

    public static ProductCategoryListResponseDto toDto(String productCode) {
        return ProductCategoryListResponseDto.builder()
                .productCode(productCode).build();
    }

    public GetProductCategoryListResponseVo toVo() {
        return GetProductCategoryListResponseVo.builder()
                .productCode(productCode).build();
    }

}
