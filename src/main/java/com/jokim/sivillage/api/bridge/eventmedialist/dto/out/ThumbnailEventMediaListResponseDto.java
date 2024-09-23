package com.jokim.sivillage.api.bridge.eventmedialist.dto.out;

import com.jokim.sivillage.api.bridge.eventmedialist.vo.out.GetThumbnailEventMediaListResponseVo;
import com.querydsl.core.annotations.QueryProjection;

public class ThumbnailEventMediaListResponseDto {

    private final String mediaCode;
    private final String mediaUrl;
    private final String mediaType;

    @QueryProjection
    public ThumbnailEventMediaListResponseDto(String mediaCode, String mediaUrl, String mediaType) {
        this.mediaCode = mediaCode;
        this.mediaUrl = mediaUrl;
        this.mediaType = mediaType;
    }

    public GetThumbnailEventMediaListResponseVo toVo() {
        return GetThumbnailEventMediaListResponseVo.builder()
            .mediaCode(mediaCode)
            .mediaUrl(mediaUrl)
            .mediaType(mediaType)
            .build();
    }

}
