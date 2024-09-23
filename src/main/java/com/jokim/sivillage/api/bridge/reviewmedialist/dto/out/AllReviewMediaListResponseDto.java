package com.jokim.sivillage.api.bridge.reviewmedialist.dto.out;

import com.jokim.sivillage.api.bridge.reviewmedialist.domain.ReviewMediaList;
import com.jokim.sivillage.api.bridge.reviewmedialist.vo.out.GetAllReviewMediaListResponseVo;
import com.querydsl.core.annotations.QueryProjection;

public class AllReviewMediaListResponseDto {

    private final String mediaCode;
    private final String mediaUrl;
    private final String mediaType;

    @QueryProjection
    public AllReviewMediaListResponseDto(String mediaCode, String mediaUrl, String mediaType) {
        this.mediaCode = mediaCode;
        this.mediaUrl = mediaUrl;
        this.mediaType = mediaType;
    }

    public GetAllReviewMediaListResponseVo toVo() {

        return GetAllReviewMediaListResponseVo.builder()
            .mediaCode(mediaCode)
            .mediaUrl(mediaUrl)
            .mediaType(mediaType)
            .build();
    }

}
