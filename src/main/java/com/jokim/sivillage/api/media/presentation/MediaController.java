package com.jokim.sivillage.api.media.presentation;

import com.jokim.sivillage.api.media.application.MediaService;
import com.jokim.sivillage.api.media.dto.MediaRequestDto;
import com.jokim.sivillage.api.media.vo.in.AddMediaRequestVo;
import com.jokim.sivillage.api.media.vo.in.UpdateMediaRequestVo;
import com.jokim.sivillage.api.media.vo.out.GetMediaResponseVo;
import com.jokim.sivillage.common.entity.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

    @Operation(summary = "Media 조회 API")
    @GetMapping("/{mediaCode}")
    public BaseResponse<GetMediaResponseVo> getMedia(@PathVariable String mediaCode) {

        return new BaseResponse<>(mediaService.getMedia(mediaCode).toVo());
    }

    @Operation(summary = "Media 정보 수정 API")
    @PutMapping
    public BaseResponse<Void> updateMedia(@RequestBody UpdateMediaRequestVo updateMediaRequestVo) {

        mediaService.updateMedia(MediaRequestDto.toDto(updateMediaRequestVo));
        return new BaseResponse<>();
    }

    @Operation(summary = "Media 삭제 API")
    @DeleteMapping("/{mediaCode}")
    public BaseResponse<Void> deleteMedia(@PathVariable String mediaCode) {

        mediaService.deleteMedia(mediaCode);
        return new BaseResponse<>();
    }

}
