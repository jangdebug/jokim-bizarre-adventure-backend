package com.jokim.sivillage.api.bridge.eventmedialist.dto;

import com.jokim.sivillage.api.bridge.eventmedialist.domain.EventMediaList;
import com.jokim.sivillage.api.bridge.eventmedialist.vo.out.GetEventMediaListResponseVo;
import lombok.Builder;

@Builder
public class EventMediaListResponseDto {

    private String mediaCode;
    private Boolean isThumbnail;

    public static EventMediaListResponseDto toDto(EventMediaList eventMediaList) {
        return EventMediaListResponseDto.builder()
            .mediaCode(eventMediaList.getMediaCode())
            .isThumbnail(eventMediaList.getIsThumbnail())
            .build();
    }

    public GetEventMediaListResponseVo toVo() {
        return GetEventMediaListResponseVo.builder()
            .mediaCode(mediaCode)
            .isThumbnail(isThumbnail)
            .build();
    }

}
