package com.jokim.sivillage.api.bridge.brandmedialist.dto.in;

import com.jokim.sivillage.api.bridge.brandmedialist.domain.BrandMediaList;
import com.jokim.sivillage.api.bridge.brandmedialist.vo.in.AddBrandMediaListRequestVo;
import com.jokim.sivillage.api.bridge.brandmedialist.vo.in.UpdateBrandMediaListRequestVo;
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

    public static BrandMediaListRequestDto toDto(
        UpdateBrandMediaListRequestVo updateBrandMediaListRequestVo) {

        return BrandMediaListRequestDto.builder()
            .brandCode(updateBrandMediaListRequestVo.getBrandCode())
            .mediaCode(updateBrandMediaListRequestVo.getMediaCode())
            .build();
    }

    public BrandMediaList toEntity(Boolean isLogo) {
        return BrandMediaList.builder()
            .brandCode(brandCode)
            .mediaCode(mediaCode)
            .isLogo(isLogo)
            .build();
    }

    public BrandMediaList toEntity(Long id, Boolean isLogo) {   // update newLogo-BrandMediaList
        return BrandMediaList.builder()
            .id(id)
            .brandCode(brandCode)
            .mediaCode(mediaCode)
            .isLogo(isLogo)
            .build();
    }

    public BrandMediaList toEntity(Long id, String mediaCode, Boolean isLogo) { // update oldLogo-BrandMediaList
        return BrandMediaList.builder()
            .id(id)
            .brandCode(brandCode)
            .mediaCode(mediaCode)
            .isLogo(isLogo)
            .build();
    }

}
