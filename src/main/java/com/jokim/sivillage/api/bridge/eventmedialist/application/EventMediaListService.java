package com.jokim.sivillage.api.bridge.eventmedialist.application;

import com.jokim.sivillage.api.bridge.eventmedialist.dto.in.EventMediaListRequestDto;
import com.jokim.sivillage.api.bridge.eventmedialist.dto.out.AllEventMediaListResponseDto;
import java.util.List;

public interface EventMediaListService {

    void addEventMediaList(EventMediaListRequestDto eventMediaListRequestDto);

    List<AllEventMediaListResponseDto> getAllEventMediaLists(String eventCode);

    void updateEventMediaList(EventMediaListRequestDto eventMediaListRequestDto);

}
