package com.jokim.sivillage.api.bridge.dto.in;

import com.jokim.sivillage.api.bridge.domain.ProductMediaList;
import com.jokim.sivillage.api.bridge.vo.in.AddProductMediaListRequestVo;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProductMediaListRequestDto {

    private String productCode;
    private String mediaCode;

    public static ProductMediaListRequestDto toDto(
            AddProductMediaListRequestVo addProductMediaListRequestVo) {

        return ProductMediaListRequestDto.builder()
                .productCode(addProductMediaListRequestVo.getProductCode())
                .mediaCode(addProductMediaListRequestVo.getMediaCode())
                .build();
    }

    public ProductMediaList toEntity(Boolean isThumbnail) {
        return ProductMediaList.builder()
                .productCode(productCode)
                .mediaCode(mediaCode)
                .isThumbnail(isThumbnail)
                .build();
    }

}
