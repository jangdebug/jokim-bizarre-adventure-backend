package com.jokim.sivillage.api.bridge.eventmedialist.application;

import com.jokim.sivillage.api.bridge.eventmedialist.dto.EventMediaListRequestDto;
import com.jokim.sivillage.api.bridge.eventmedialist.dto.EventMediaListResponseDto;
import java.util.List;

public interface EventMediaListService {

    void addEventMediaList(EventMediaListRequestDto eventMediaListRequestDto);

    List<EventMediaListResponseDto> getEventMediaList(String eventCode);

}
