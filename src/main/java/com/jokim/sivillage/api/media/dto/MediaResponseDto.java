package com.jokim.sivillage.api.media.dto;

import com.jokim.sivillage.api.media.domain.Media;
import com.jokim.sivillage.api.media.vo.out.GetMediaResponseVo;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MediaResponseDto {

    private String mediaCode;
    private String url;
    private String name;
    private String type;

    public static MediaResponseDto toDto(Media media) {
        return MediaResponseDto.builder()
                .mediaCode(media.getMediaCode())
                .url(media.getUrl())
                .name(media.getName())
                .type(String.valueOf(media.getType()))
                .build();
    }

    public GetMediaResponseVo toVo() {
        return GetMediaResponseVo.builder()
                .mediaCode(mediaCode)
                .url(url)
                .name(name)
                .type(type)
                .build();
    }

}
