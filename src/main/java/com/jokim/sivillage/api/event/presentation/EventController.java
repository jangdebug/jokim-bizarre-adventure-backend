package com.jokim.sivillage.api.event.presentation;


import com.jokim.sivillage.api.event.application.EventService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/v1")
@RestController
@RequiredArgsConstructor
@Slf4j
public class EventController {

    private final EventService eventService;


}
