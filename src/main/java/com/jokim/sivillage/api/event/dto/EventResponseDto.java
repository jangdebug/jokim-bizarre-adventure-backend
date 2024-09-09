package com.jokim.sivillage.api.event.dto;

import lombok.Builder;
import lombok.Setter;

@Builder
@Setter
public class EventResponseDto {

    // 컬럼 수정 필요
    private Long id;
    private Long eventId;
    private String brandName;
    private Boolean hasMoreBrands;
    private String title;
    private String subTitle;
    private String thumbnailUrl;
    ///////////////////////////////////////

}
