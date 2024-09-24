package com.jokim.sivillage.api.bridge.eventmedialist.dto.in;

import com.jokim.sivillage.api.bridge.eventmedialist.domain.EventMediaList;
import com.jokim.sivillage.api.bridge.eventmedialist.vo.in.AddEventMediaListRequestVo;
import com.jokim.sivillage.api.bridge.eventmedialist.vo.in.UpdateEventMediaListRequestVo;
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

    public static EventMediaListRequestDto toDto(
        UpdateEventMediaListRequestVo updateEventMediaListRequestVo) {

        return EventMediaListRequestDto.builder()
            .eventCode(updateEventMediaListRequestVo.getEventCode())
            .mediaCode(updateEventMediaListRequestVo.getMediaCode())
            .build();
    }

    public EventMediaList toEntity() {      // add EventMediaList
        return EventMediaList.builder()
            .eventCode(eventCode)
            .mediaCode(mediaCode)
            .isThumbnail(isThumbnail)
            .build();
    }

    public EventMediaList toEntity(Long id, Boolean isThumbnail) {  // update newThumbnail-EventMediaList
        return EventMediaList.builder()
            .id(id)
            .eventCode(eventCode)
            .mediaCode(mediaCode)
            .isThumbnail(isThumbnail)
            .build();
    }

    public EventMediaList toEntity(Long id, String mediaCode, Boolean isThumbnail) {    // update oldThumbnail-EventMediaList
        return EventMediaList.builder()
            .id(id)
            .eventCode(eventCode)
            .mediaCode(mediaCode)
            .isThumbnail(isThumbnail)
            .build();
    }

}
