package com.jokim.sivillage.api.bridge.productmedialist.dto.out;

import com.jokim.sivillage.api.bridge.productmedialist.vo.out.GetAllProductMediaListsResponseVo;
import com.querydsl.core.annotations.QueryProjection;

public class AllProductMediaListsResponseDto {

    private final String mediaCode;
    private final String mediaUrl;
    private final String mediaType;
    private final Boolean isThumbnail;

    @QueryProjection
    public AllProductMediaListsResponseDto(String mediaCode, String mediaUrl,
        String mediaType, Boolean isThumbnail) {

        this.mediaCode = mediaCode;
        this.mediaUrl = mediaUrl;
        this.mediaType = mediaType;
        this.isThumbnail = isThumbnail;
    }

    public GetAllProductMediaListsResponseVo toVo() {
        return GetAllProductMediaListsResponseVo.builder()
            .mediaCode(mediaCode)
            .mediaUrl(mediaUrl)
            .mediaType(mediaType)
            .isThumbnail(isThumbnail)
            .build();
    }

}
