package com.jokim.sivillage.api.event.application;

import com.jokim.sivillage.api.event.dto.EventResponseDto;

import java.util.List;

public interface EventService {

    List<EventResponseDto> getEvents();

}
