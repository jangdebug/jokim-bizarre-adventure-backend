package com.jokim.sivillage.api.bridge.productmedialist.dto.out;

import com.jokim.sivillage.api.bridge.productmedialist.vo.out.GetProductMediaListResponseVo;
import com.querydsl.core.annotations.QueryProjection;

public class ProductMediaListResponseDto {

    private final String mediaCode;
    private final String mediaUrl;
    private final String mediaName;
    private final String mediaType;
    private final Boolean isThumbnail;

    @QueryProjection
    public ProductMediaListResponseDto(String mediaCode, String mediaUrl, String mediaName,
        String mediaType, Boolean isThumbnail) {
        this.mediaCode = mediaCode;
        this.mediaUrl = mediaUrl;
        this.mediaName = mediaName;
        this.mediaType = mediaType;
        this.isThumbnail = isThumbnail;
    }

    public GetProductMediaListResponseVo toVo() {
        return GetProductMediaListResponseVo.builder()
            .mediaCode(mediaCode)
            .mediaUrl(mediaUrl)
            .mediaName(mediaName)
            .mediaType(mediaType)
            .isThumbnail(isThumbnail)
            .build();
    }

}
