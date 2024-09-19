package com.jokim.sivillage.api.bridge.eventmedialist.presentation;

import com.jokim.sivillage.api.bridge.eventmedialist.application.EventMediaListService;
import com.jokim.sivillage.api.bridge.eventmedialist.dto.EventMediaListRequestDto;
import com.jokim.sivillage.api.bridge.eventmedialist.vo.in.AddEventMediaListRequestVo;
import com.jokim.sivillage.common.entity.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Bridge")
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/event-media")
public class EventMediaListController {

    private final EventMediaListService eventMediaListService;

    @Operation(summary = "Event-Media-List 생성 API")
    @PostMapping
    public BaseResponse<Void> addEventMediaList(
        @RequestBody AddEventMediaListRequestVo addEventMediaListRequestVo) {

        eventMediaListService.addEventMediaList(EventMediaListRequestDto.toDto(
            addEventMediaListRequestVo));
        return new BaseResponse<>();
    }

}
