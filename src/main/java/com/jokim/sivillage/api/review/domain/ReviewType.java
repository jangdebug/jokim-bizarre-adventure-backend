package com.jokim.sivillage.api.review.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ReviewType {
    PHOTO("사진"),
    NORMAL("일반"),
    PREVIEW("시사");

    private final String reviewType;

    @JsonValue
    public String getReviewType() {
        return reviewType;
    }

    @JsonCreator
    public static ReviewType fromString(String value) {
        for (ReviewType reviewType: ReviewType.values()) {
            if (reviewType.reviewType.equals(value)) {
                return reviewType;
            }
        }
        throw new IllegalArgumentException("UnKnown value: " + value);
    }
}
