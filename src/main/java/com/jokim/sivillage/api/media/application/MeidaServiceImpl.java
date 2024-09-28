package com.jokim.sivillage.api.media.application;

import com.jokim.sivillage.api.media.domain.Media;
import com.jokim.sivillage.api.media.dto.MediaRequestDto;
import com.jokim.sivillage.api.media.dto.MediaResponseDto;
import com.jokim.sivillage.api.media.infrastructure.MediaRepository;
import com.jokim.sivillage.common.exception.BaseException;
import com.jokim.sivillage.common.utils.CodeGenerator;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.jokim.sivillage.common.entity.BaseResponseStatus.FAILED_TO_GENERATE_MEDIA_CODE;
import static com.jokim.sivillage.common.entity.BaseResponseStatus.NOT_EXIST_MEDIA;

@RequiredArgsConstructor
@Service
public class MeidaServiceImpl implements MediaService {

    private final MediaRepository mediaRepository;

    private static final int MAX_CODE_TRIES = 5;

    @Transactional
    @Override
    public void addMedia(MediaRequestDto mediaRequestDto) {

        String mediaCode = generateUniqueMediaCode();

        mediaRepository.save(mediaRequestDto.toEntity(mediaCode));
    }

    @Transactional(readOnly = true)
    @Override
    public MediaResponseDto getMedia(String mediaCode) {
        return MediaResponseDto.toDto(mediaRepository.findByMediaCode(mediaCode)
            .orElseThrow(() -> new BaseException(NOT_EXIST_MEDIA)));
    }

    @Transactional
    @Override
    public void updateMedia(MediaRequestDto mediaRequestDto) {
        Media media = mediaRepository.findByMediaCode(mediaRequestDto.getMediaCode())
                .orElseThrow(() -> new BaseException(NOT_EXIST_MEDIA));

        mediaRepository.save(mediaRequestDto.toEntity(media.getId()));
    }

    @Transactional
    @Override
    public void deleteMedia(String mediaCode) {

        mediaRepository.deleteByMediaCode(mediaCode);
    }

    private String generateUniqueMediaCode() {
        for (int i = 0; i < MAX_CODE_TRIES; i++) {
            String mediaCode = CodeGenerator.generateCode("MD");

            if (!mediaRepository.existsByMediaCode(mediaCode)) return mediaCode;
        }

        throw new BaseException(FAILED_TO_GENERATE_MEDIA_CODE);
    }

}
