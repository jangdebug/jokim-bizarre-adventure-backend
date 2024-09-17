package com.jokim.sivillage.api.media.presentation;

import com.jokim.sivillage.api.media.application.MediaService;
import com.jokim.sivillage.api.media.dto.MediaRequestDto;
import com.jokim.sivillage.api.media.vo.AddMediaRequestVo;
import com.jokim.sivillage.common.entity.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Media")
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/media")
public class MediaController {

    private final MediaService mediaService;

    @Operation(summary = "Media 생성 API")
    @PostMapping
    public BaseResponse<Void> addMedia(@RequestBody AddMediaRequestVo addMediaRequestVo) {

        mediaService.addMedia(MediaRequestDto.toDto(addMediaRequestVo));

        return new BaseResponse<>();
    }

}
