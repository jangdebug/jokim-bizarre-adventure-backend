package com.jokim.sivillage.api.bridge.reviewmedialist.dto.out;

import com.jokim.sivillage.api.bridge.reviewmedialist.vo.out.GetAllReviewMediaListsResponseVo;
import com.querydsl.core.annotations.QueryProjection;

public class AllReviewMediaListsResponseDto {

    private final String mediaCode;
    private final String mediaUrl;
    private final String mediaType;

    @QueryProjection
    public AllReviewMediaListsResponseDto(String mediaCode, String mediaUrl, String mediaType) {
        this.mediaCode = mediaCode;
        this.mediaUrl = mediaUrl;
        this.mediaType = mediaType;
    }

    public GetAllReviewMediaListsResponseVo toVo() {

        return GetAllReviewMediaListsResponseVo.builder()
            .mediaCode(mediaCode)
            .mediaUrl(mediaUrl)
            .mediaType(mediaType)
            .build();
    }

}
