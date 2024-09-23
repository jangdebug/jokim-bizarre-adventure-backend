package com.jokim.sivillage.api.bridge.productmedialist.dto.out;

import com.jokim.sivillage.api.bridge.productmedialist.domain.ProductMediaList;
import com.jokim.sivillage.api.bridge.productmedialist.vo.out.GetProductMediaListResponseVo;
import lombok.Builder;

@Builder
public class ProductMediaListResponseDto {

    private String mediaCode;
    private Boolean isThumbnail;

    public static ProductMediaListResponseDto toDto(ProductMediaList productMediaList) {
        return ProductMediaListResponseDto.builder()
                .mediaCode(productMediaList.getMediaCode())
                .isThumbnail(productMediaList.getIsThumbnail())
                .build();
    }

    public GetProductMediaListResponseVo toVo() {
        return GetProductMediaListResponseVo.builder()
                .mediaCode(mediaCode)
                .isThumbnail(isThumbnail)
                .build();
    }

}
