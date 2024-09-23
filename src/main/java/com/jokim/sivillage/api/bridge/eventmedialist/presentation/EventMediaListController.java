package com.jokim.sivillage.api.bridge.eventmedialist.presentation;

import com.jokim.sivillage.api.bridge.eventmedialist.application.EventMediaListService;
import com.jokim.sivillage.api.bridge.eventmedialist.dto.in.EventMediaListRequestDto;
import com.jokim.sivillage.api.bridge.eventmedialist.dto.out.AllEventMediaListResponseDto;
import com.jokim.sivillage.api.bridge.eventmedialist.vo.in.AddEventMediaListRequestVo;
import com.jokim.sivillage.api.bridge.eventmedialist.vo.in.UpdateEventMediaListRequestVo;
import com.jokim.sivillage.api.bridge.eventmedialist.vo.out.GetAllEventMediaListResponseVo;
import com.jokim.sivillage.common.entity.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Event-Media")
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

    @Operation(summary = "Event-Media-List 전체 조회 API")
    @GetMapping("/{eventCode}")
    public BaseResponse<List<GetAllEventMediaListResponseVo>> getAllEventMediaLists(
        @PathVariable String eventCode) {

        return new BaseResponse<>(
            eventMediaListService.getAllEventMediaLists(eventCode)
                .stream().map(AllEventMediaListResponseDto::toVo).toList());
    }

    @Operation(summary = "Event-Media-List 썸네일 수정 API")
    @PutMapping
    public BaseResponse<Void> updateEventMediaList(
        @RequestBody UpdateEventMediaListRequestVo updateEventMediaListRequestVo) {

        eventMediaListService.updateEventMediaList(EventMediaListRequestDto.toDto(
            updateEventMediaListRequestVo));
        return new BaseResponse<>();
    }

}
