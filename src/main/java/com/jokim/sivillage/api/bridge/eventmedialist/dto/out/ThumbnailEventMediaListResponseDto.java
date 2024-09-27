package com.jokim.sivillage.api.bridge.eventmedialist.dto.out;

import com.jokim.sivillage.api.bridge.eventmedialist.vo.out.GetThumbnailEventMediaListResponseVo;
import com.querydsl.core.annotations.QueryProjection;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ThumbnailEventMediaListResponseDto {

    private String mediaCode;
    private String mediaUrl;
    private String mediaType;

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
