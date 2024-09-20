package com.jokim.sivillage.api.bridge.brandmedialist.dto;

import com.jokim.sivillage.api.bridge.brandmedialist.domain.BrandMediaList;
import com.jokim.sivillage.api.bridge.brandmedialist.vo.GetBrandMediaListResponseVo;
import lombok.Builder;

@Builder
public class BrandMediaListResponseDto {

    private String mediaCode;

    public static BrandMediaListResponseDto toDto(BrandMediaList brandMediaList) {
        return BrandMediaListResponseDto.builder()
            .mediaCode(brandMediaList.getMediaCode())
            .build();
    }

    public GetBrandMediaListResponseVo toVo() {
        return GetBrandMediaListResponseVo.builder()
            .mediaCode(mediaCode)
            .build();
    }

}
