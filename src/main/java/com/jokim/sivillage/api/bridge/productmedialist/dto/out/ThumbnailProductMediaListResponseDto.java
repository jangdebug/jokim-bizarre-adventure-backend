package com.jokim.sivillage.api.bridge.productmedialist.dto.out;

import com.jokim.sivillage.api.bridge.productmedialist.vo.out.GetThumbnailProductMediaListResponseVo;
import com.querydsl.core.annotations.QueryProjection;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ThumbnailProductMediaListResponseDto {

    private String mediaCode;
    private String mediaUrl;
    private String mediaType;

    @QueryProjection
    public ThumbnailProductMediaListResponseDto(String mediaCode, String mediaUrl,
        String mediaType) {
        this.mediaCode = mediaCode;
        this.mediaUrl = mediaUrl;
        this.mediaType = mediaType;
    }

    public GetThumbnailProductMediaListResponseVo toVo() {
        return GetThumbnailProductMediaListResponseVo.builder()
            .mediaCode(mediaCode)
            .mediaUrl(mediaUrl)
            .mediaType(mediaType)
            .build();
    }

}
