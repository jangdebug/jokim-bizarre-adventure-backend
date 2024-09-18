package com.jokim.sivillage.api.bridge.dto.out;

import com.jokim.sivillage.api.bridge.domain.ProductMediaList;
import com.jokim.sivillage.api.bridge.vo.out.GetProductMediaListResponseVo;
import lombok.Builder;

@Builder
public class ProductMediaListResponseDto {

    private String mediaCode;

    public static ProductMediaListResponseDto toDto(ProductMediaList productMediaList) {
        return ProductMediaListResponseDto.builder()
                .mediaCode(productMediaList.getMediaCode())
                .build();
    }

    public GetProductMediaListResponseVo toVo() {
        return GetProductMediaListResponseVo.builder()
                .mediaCode(mediaCode)
                .build();
    }

}
