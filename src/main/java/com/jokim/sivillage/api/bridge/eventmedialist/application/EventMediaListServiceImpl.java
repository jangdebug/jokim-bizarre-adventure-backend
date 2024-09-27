package com.jokim.sivillage.api.bridge.eventmedialist.application;

import static com.jokim.sivillage.common.entity.BaseResponseStatus.ALREADY_EXIST_THUMBNAIL;
import static com.jokim.sivillage.common.entity.BaseResponseStatus.NOT_EXIST_MEDIA;

import com.jokim.sivillage.api.bridge.eventmedialist.domain.EventMediaList;
import com.jokim.sivillage.api.bridge.eventmedialist.dto.in.EventMediaListRequestDto;
import com.jokim.sivillage.api.bridge.eventmedialist.dto.out.AllEventMediaListsResponseDto;
import com.jokim.sivillage.api.bridge.eventmedialist.dto.out.ThumbnailEventMediaListResponseDto;
import com.jokim.sivillage.api.bridge.eventmedialist.infrastructure.EventMediaListRepository;
import com.jokim.sivillage.api.bridge.eventmedialist.infrastructure.EventMediaListRepositoryCustom;
import com.jokim.sivillage.common.exception.BaseException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class EventMediaListServiceImpl implements EventMediaListService {

    private final EventMediaListRepository eventMediaListRepository;
    private final EventMediaListRepositoryCustom eventMediaListRepositoryCustom;

    @Transactional
    @Override
    public void addEventMediaList(EventMediaListRequestDto eventMediaListRequestDto) {
        if (eventMediaListRequestDto.getIsThumbnail() &&
            eventMediaListRepository.existsByEventCodeAndIsThumbnail(
                eventMediaListRequestDto.getEventCode(), true))
            throw new BaseException(ALREADY_EXIST_THUMBNAIL);

        eventMediaListRepository.save(eventMediaListRequestDto.toEntity());
    }

    @Transactional(readOnly = true)
    @Override
    public List<AllEventMediaListsResponseDto> getAllEventMediaLists(String eventCode) {
        return eventMediaListRepositoryCustom.getAllEventMediaLists(eventCode);
    }

    @Override
    public ThumbnailEventMediaListResponseDto getThumbnailByEventCode(String eventCode) {
        return eventMediaListRepositoryCustom.getThumbnailByEventCode(eventCode)
            .orElse(new ThumbnailEventMediaListResponseDto());
    }

    @Transactional
    @Override
    public void updateEventMediaList(EventMediaListRequestDto eventMediaListRequestDto) {

        Long newThumbnailMediaId = eventMediaListRepository.findByEventCodeAndMediaCode(
            eventMediaListRequestDto.getEventCode(), eventMediaListRequestDto.getMediaCode())
            .orElseThrow(() -> new BaseException(NOT_EXIST_MEDIA)).getId();

        List<EventMediaList> oldThumbnailMediaList = eventMediaListRepository.findByEventCodeAndIsThumbnail(
            eventMediaListRequestDto.getEventCode(), true);

        for(EventMediaList oldThumbnailMedia : oldThumbnailMediaList) {
            eventMediaListRepository.save(
                eventMediaListRequestDto.toEntity(
                    oldThumbnailMedia.getId(), oldThumbnailMedia.getMediaCode(), false));
        }

        eventMediaListRepository.save(
            eventMediaListRequestDto.toEntity(newThumbnailMediaId, true));
    }

}
