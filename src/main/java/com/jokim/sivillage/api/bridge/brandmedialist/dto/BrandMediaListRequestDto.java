package com.jokim.sivillage.api.bridge.brandmedialist.dto;

import com.jokim.sivillage.api.bridge.brandmedialist.domain.BrandMediaList;
import com.jokim.sivillage.api.bridge.brandmedialist.vo.AddBrandMediaListRequestVo;
import lombok.Builder;
import lombok.Getter;

@Builder
public class BrandMediaListRequestDto {

    private String brandCode;
    private String mediaCode;

    public static BrandMediaListRequestDto toDto(
        AddBrandMediaListRequestVo addBrandMediaListRequestVo) {

        return BrandMediaListRequestDto.builder()
            .brandCode(addBrandMediaListRequestVo.getBrandCode())
            .mediaCode(addBrandMediaListRequestVo.getMediaCode())
            .build();
    }

    public BrandMediaList toEntity() {
        return BrandMediaList.builder()
            .brandCode(brandCode)
            .mediaCode(mediaCode)
            .build();
    }

}
