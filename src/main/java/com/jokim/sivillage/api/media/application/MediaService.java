package com.jokim.sivillage.api.media.application;

import com.jokim.sivillage.api.media.dto.MediaRequestDto;
import com.jokim.sivillage.api.media.dto.MediaResponseDto;

public interface MediaService {

    void addMedia(MediaRequestDto mediaRequestDto);

    MediaResponseDto getMedia(String mediaCode);

    void updateMedia(MediaRequestDto mediaRequestDto);

    void deleteMedia(String mediaCode);

}
