package com.jokim.sivillage.api.customer.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum State {

    ACTIVATION("활동"),
    SLEEP("휴면"),
    AWAITDELETION("탈퇴대기");

    private final String state;

    @JsonValue
    public String getState() {
        return state;
    }

    @JsonCreator
    public static State fromString(String value) {
        for (State state : State.values()) {
            if (state.state.equals(value)) {
                return state;
            }
        }
        throw new IllegalArgumentException("UnKnown value: " + value);
    }

}