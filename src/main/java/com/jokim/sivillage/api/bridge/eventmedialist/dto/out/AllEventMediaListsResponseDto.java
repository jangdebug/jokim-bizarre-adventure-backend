package com.jokim.sivillage.api.bridge.eventmedialist.dto.out;

import com.jokim.sivillage.api.bridge.eventmedialist.vo.out.GetAllEventMediaListsResponseVo;
import com.querydsl.core.annotations.QueryProjection;

public class AllEventMediaListsResponseDto {

    private final String mediaCode;
    private final String mediaUrl;
    private final String mediaType;
    private final Boolean isThumbnail;

    @QueryProjection
    public AllEventMediaListsResponseDto(String mediaCode, String mediaUrl, String mediaType,
        Boolean isThumbnail) {
        this.mediaCode = mediaCode;
        this.mediaUrl = mediaUrl;
        this.mediaType = mediaType;
        this.isThumbnail = isThumbnail;
    }

    public GetAllEventMediaListsResponseVo toVo() {
        return GetAllEventMediaListsResponseVo.builder()
            .mediaCode(mediaCode)
            .mediaUrl(mediaUrl)
            .mediaType(mediaType)
            .isThumbnail(isThumbnail)
            .build();
    }

}
