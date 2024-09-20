package com.jokim.sivillage.api.media.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MediaType {

    IMAGE("image"),
    VIDEO("video");

    private final String mediaType;

}
