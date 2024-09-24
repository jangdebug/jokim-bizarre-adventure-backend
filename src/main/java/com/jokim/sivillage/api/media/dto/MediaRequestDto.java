package com.jokim.sivillage.api.media.dto;

import com.jokim.sivillage.api.media.domain.Media;
import com.jokim.sivillage.api.media.domain.MediaType;
import com.jokim.sivillage.api.media.vo.in.AddMediaRequestVo;
import com.jokim.sivillage.api.media.vo.in.UpdateMediaRequestVo;
import com.jokim.sivillage.common.exception.BaseException;
import lombok.Builder;
import lombok.Getter;

import static com.jokim.sivillage.common.entity.BaseResponseStatus.INVALID_MEDIA_TYPE;

@Getter
@Builder
public class MediaRequestDto {

    private String mediaCode;
    private String url;
    private String name;
    private String mediaType;

    public static MediaRequestDto toDto(AddMediaRequestVo addMediaRequestVo) {
        return MediaRequestDto.builder()
                .url(addMediaRequestVo.getUrl())
                .name(addMediaRequestVo.getName())
                .mediaType(addMediaRequestVo.getMediaType())
                .build();
    }

    public static MediaRequestDto toDto(UpdateMediaRequestVo updateMediaRequestVo) {
        return MediaRequestDto.builder()
                .mediaCode(updateMediaRequestVo.getMediaCode())
                .url(updateMediaRequestVo.getUrl())
                .name(updateMediaRequestVo.getName())
                .mediaType(updateMediaRequestVo.getMediaType())
                .build();
    }

    public Media toEntity(String mediaCode) {   // add Media
        try{
            return Media.builder()
                    .mediaCode(mediaCode)
                    .url(url)
                    .name(name)
                    .mediaType(MediaType.valueOf(mediaType.toUpperCase()))  // enum type
                    .build();
        } catch(RuntimeException e) {
            throw new BaseException(INVALID_MEDIA_TYPE);
        }

    }

    public Media toEntity(Long id) {
        try{
            return Media.builder()
                    .id(id)
                    .mediaCode(mediaCode)
                    .url(url)
                    .name(name)
                    .mediaType(MediaType.valueOf(mediaType.toUpperCase()))  // enum type
                    .build();
        } catch(RuntimeException e) {
            throw new BaseException(INVALID_MEDIA_TYPE);
        }
    }



}
