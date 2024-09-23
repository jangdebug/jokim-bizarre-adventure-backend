package com.jokim.sivillage.api.bridge.eventmedialist.infrastructure;

import com.jokim.sivillage.api.bridge.eventmedialist.dto.out.AllEventMediaListResponseDto;
import com.jokim.sivillage.api.bridge.eventmedialist.dto.out.ThumbnailEventMediaListResponseDto;
import java.util.List;

public interface EventMediaListRepositoryCustom {

    List<AllEventMediaListResponseDto> getAllEventMediaLists(String eventCode);

    ThumbnailEventMediaListResponseDto getThumbnailByEventCode(String eventCode);

}
