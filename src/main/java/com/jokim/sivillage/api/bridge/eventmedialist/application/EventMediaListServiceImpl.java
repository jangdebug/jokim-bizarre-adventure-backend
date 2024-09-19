package com.jokim.sivillage.api.bridge.eventmedialist.application;

import static com.jokim.sivillage.common.entity.BaseResponseStatus.ALREADY_EXIST_THUMBNAIL;

import com.jokim.sivillage.api.bridge.eventmedialist.dto.EventMediaListRequestDto;
import com.jokim.sivillage.api.bridge.eventmedialist.infrastructure.EventMediaListRepository;
import com.jokim.sivillage.common.exception.BaseException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class EventMediaListServiceImpl implements EventMediaListService {

    private final EventMediaListRepository eventMediaListRepository;

    @Transactional
    @Override
    public void addEventMediaList(EventMediaListRequestDto eventMediaListRequestDto) {
        if (eventMediaListRequestDto.getIsThumbnail() &&
            eventMediaListRepository.existsByEventCodeAndIsThumbnail(
                eventMediaListRequestDto.getEventCode(), true))
            throw new BaseException(ALREADY_EXIST_THUMBNAIL);

        eventMediaListRepository.save(eventMediaListRequestDto.toEntity());
    }
}
