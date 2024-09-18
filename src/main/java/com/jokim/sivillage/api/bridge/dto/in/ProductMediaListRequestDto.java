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
    private Boolean isThumbnail;

    public static ProductMediaListRequestDto toDto(
            AddProductMediaListRequestVo addProductMediaListRequestVo) {

        return ProductMediaListRequestDto.builder()
                .productCode(addProductMediaListRequestVo.getProductCode())
                .mediaCode(addProductMediaListRequestVo.getMediaCode())
                .isThumbnail(addProductMediaListRequestVo.getIsThumbnail())
                .build();
    }

    public ProductMediaList toEntity() {
        return ProductMediaList.builder()
                .productCode(productCode)
                .mediaCode(mediaCode)
                .isThumbnail(isThumbnail == null ? false : isThumbnail)
                .build();
    }

}
