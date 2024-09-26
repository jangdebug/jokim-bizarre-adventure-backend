package com.jokim.sivillage.api.bridge.eventmedialist.infrastructure;

import com.jokim.sivillage.api.bridge.eventmedialist.dto.out.AllEventMediaListsResponseDto;
import com.jokim.sivillage.api.bridge.eventmedialist.dto.out.ThumbnailEventMediaListResponseDto;
import java.util.List;
import java.util.Optional;

public interface EventMediaListRepositoryCustom {

    List<AllEventMediaListsResponseDto> getAllEventMediaLists(String eventCode);

    Optional<ThumbnailEventMediaListResponseDto> getThumbnailByEventCode(String eventCode);

}
