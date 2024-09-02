package com.jokim.sivillage.event.dto;

import lombok.Builder;
import lombok.Setter;

@Builder
@Setter
public class EventResponseDto {

    private Long id;
    private Long eventId;
    private String brandName;
    private Boolean hasMoreBrands;
    private String title;
    private String subTitle;
    private String thumbnailUrl;





}
