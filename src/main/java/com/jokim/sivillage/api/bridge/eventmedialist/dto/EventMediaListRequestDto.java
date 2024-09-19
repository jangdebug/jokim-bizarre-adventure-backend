package com.jokim.sivillage.api.bridge.eventmedialist.dto;

import com.jokim.sivillage.api.bridge.eventmedialist.domain.EventMediaList;
import com.jokim.sivillage.api.bridge.eventmedialist.vo.in.AddEventMediaListRequestVo;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class EventMediaListRequestDto {

    private String eventCode;
    private String mediaCode;
    private Boolean isThumbnail;

    public static EventMediaListRequestDto toDto(
        AddEventMediaListRequestVo addEventMediaListRequestVo) {

        return EventMediaListRequestDto.builder()
            .eventCode(addEventMediaListRequestVo.getEventCode())
            .mediaCode(addEventMediaListRequestVo.getMediaCode())
            .isThumbnail(addEventMediaListRequestVo.getIsThumbnail())
            .build();
    }

    public EventMediaList toEntity() {
        return EventMediaList.builder()
            .eventCode(eventCode)
            .mediaCode(mediaCode)
            .isThumbnail(isThumbnail)
            .build();
    }

}
