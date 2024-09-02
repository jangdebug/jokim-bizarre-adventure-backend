package com.jokim.sivillage.event.application;

import com.jokim.sivillage.event.dto.EventResponseDto;

import java.util.List;

public interface EventService {

    List<EventResponseDto> getEvents();

}
