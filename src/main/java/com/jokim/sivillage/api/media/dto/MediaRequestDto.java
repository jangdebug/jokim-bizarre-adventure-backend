package com.jokim.sivillage.api.media.dto;

import com.jokim.sivillage.api.media.domain.Media;
import com.jokim.sivillage.api.media.domain.MediaType;
import com.jokim.sivillage.api.media.vo.AddMediaRequestVo;
import com.jokim.sivillage.common.exception.BaseException;
import lombok.Builder;
import lombok.Getter;

import static com.jokim.sivillage.common.entity.BaseResponseStatus.NOT_EXIST_MEDIA_TYPE;

@Getter
@Builder
public class MediaRequestDto {

    private String url;
    private String name;
    private String type;

    public static MediaRequestDto toDto(AddMediaRequestVo addMediaRequestVo) {
        return MediaRequestDto.builder()
                .url(addMediaRequestVo.getUrl())
                .name(addMediaRequestVo.getName())
                .type(addMediaRequestVo.getType())
                .build();
    }

    public Media toEntity(String mediaCode) {   // add Media
        try{
            return Media.builder()
                    .mediaCode(mediaCode)
                    .url(url)
                    .name(name)
                    .type(MediaType.valueOf(type.toUpperCase()))  // enum type
                    .build();
        } catch(RuntimeException e) {
            throw new BaseException(NOT_EXIST_MEDIA_TYPE);
        }

    }

}
