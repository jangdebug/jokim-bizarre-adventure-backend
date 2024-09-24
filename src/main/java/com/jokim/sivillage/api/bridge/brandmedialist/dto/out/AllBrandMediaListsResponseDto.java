package com.jokim.sivillage.api.bridge.brandmedialist.dto.out;

import com.jokim.sivillage.api.bridge.brandmedialist.domain.BrandMediaList;
import com.jokim.sivillage.api.bridge.brandmedialist.vo.out.GetAllBrandMediaListsResponseVo;
import com.querydsl.core.annotations.QueryProjection;

public class AllBrandMediaListsResponseDto {

    private final String mediaCode;
    private final String mediaUrl;
    private final String mediaType;
    private final Boolean isLogo;

    @QueryProjection
    public AllBrandMediaListsResponseDto(String mediaCode, String mediaUrl, String mediaType,
        Boolean isLogo) {
        this.mediaCode = mediaCode;
        this.mediaUrl = mediaUrl;
        this.mediaType = mediaType;
        this.isLogo = isLogo;
    }

    public GetAllBrandMediaListsResponseVo toVo() {
        return GetAllBrandMediaListsResponseVo.builder()
            .mediaCode(mediaCode)
            .mediaUrl(mediaUrl)
            .mediaType(mediaType)
            .isLogo(isLogo)
            .build();
    }

}
