package com.jokim.sivillage.api.basket.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum BasketState {

    ACTIVE("Active State"),
    REMOVED("Removed State");

    private final String basketState;

}
