package com.jokim.sivillage.api.bridge.eventmedialist.dto.out;

import com.jokim.sivillage.api.bridge.eventmedialist.domain.EventMediaList;
import com.jokim.sivillage.api.bridge.eventmedialist.vo.out.GetAllEventMediaListResponseVo;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;

public class AllEventMediaListResponseDto {

    private final String mediaCode;
    private final String mediaUrl;
    private final String mediaType;
    private final Boolean isThumbnail;

    @QueryProjection
    public AllEventMediaListResponseDto(String mediaCode, String mediaUrl, String mediaType,
        Boolean isThumbnail) {
        this.mediaCode = mediaCode;
        this.mediaUrl = mediaUrl;
        this.mediaType = mediaType;
        this.isThumbnail = isThumbnail;
    }

    public GetAllEventMediaListResponseVo toVo() {
        return GetAllEventMediaListResponseVo.builder()
            .mediaCode(mediaCode)
            .mediaUrl(mediaUrl)
            .mediaType(mediaType)
            .isThumbnail(isThumbnail)
            .build();
    }

}
