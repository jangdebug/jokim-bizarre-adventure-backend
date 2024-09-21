package com.jokim.sivillage.api.bridge.brandmedialist.dto;

import com.jokim.sivillage.api.bridge.brandmedialist.domain.BrandMediaList;
import com.jokim.sivillage.api.bridge.brandmedialist.vo.AddBrandMediaListRequestVo;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BrandMediaListRequestDto {

    private String brandCode;
    private String mediaCode;
    private Boolean isLogo;

    public static BrandMediaListRequestDto toDto(
        AddBrandMediaListRequestVo addBrandMediaListRequestVo) {

        return BrandMediaListRequestDto.builder()
            .brandCode(addBrandMediaListRequestVo.getBrandCode())
            .mediaCode(addBrandMediaListRequestVo.getMediaCode())
            .isLogo(addBrandMediaListRequestVo.getIsLogo())
            .build();
    }

    public BrandMediaList toEntity(Boolean isLogo) {
        return BrandMediaList.builder()
            .brandCode(brandCode)
            .mediaCode(mediaCode)
            .isLogo(isLogo)
            .build();
    }

}
