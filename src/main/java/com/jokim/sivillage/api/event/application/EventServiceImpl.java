package com.jokim.sivillage.api.event.application;

import com.jokim.sivillage.api.event.dto.EventResponseDto;
import com.jokim.sivillage.api.event.domain.Event;
import com.jokim.sivillage.api.event.infrastructure.EventRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    public final EventRepository eventRepository;

    public List<EventResponseDto> getEvents() {
        List<Event> eventList = eventRepository.findAllByOrderByIdDesc();
        List<EventResponseDto> eventResponseDtoList = new ArrayList<>();

//        for(Event event : eventList){
//            EventResponseDto.builder()
//                    .id()
//                    .eventId(event)
//                    .title()
//                    .subTitle()
//
//
//            eventResponseDtoList.add()

        // TODO 이벤트 모아보기(슬라이드)
//        }

        return List.of();
    }


}
