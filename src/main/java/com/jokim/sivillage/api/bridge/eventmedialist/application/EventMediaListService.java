package com.jokim.sivillage.api.bridge.eventmedialist.application;

import com.jokim.sivillage.api.bridge.eventmedialist.dto.in.EventMediaListRequestDto;
import com.jokim.sivillage.api.bridge.eventmedialist.dto.out.AllEventMediaListsResponseDto;
import com.jokim.sivillage.api.bridge.eventmedialist.dto.out.ThumbnailEventMediaListResponseDto;
import java.util.List;

public interface EventMediaListService {

    void addEventMediaList(EventMediaListRequestDto eventMediaListRequestDto);

    List<AllEventMediaListsResponseDto> getAllEventMediaLists(String eventCode);
    ThumbnailEventMediaListResponseDto getThumbnailByEventCode(String eventCode);

    void updateEventMediaList(EventMediaListRequestDto eventMediaListRequestDto);

}
