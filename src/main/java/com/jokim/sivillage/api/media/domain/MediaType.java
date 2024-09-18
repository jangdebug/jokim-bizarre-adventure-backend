package com.jokim.sivillage.api.media.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.jokim.sivillage.api.customer.domain.State;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MediaType {

    IMAGE("image"),
    VIDEO("video");

    private final String mediaType;

}
