package com.jokim.sivillage.api.wishlist.eventwishlist.dto;

import com.jokim.sivillage.api.wishlist.eventwishlist.domain.EventWishlist;
import com.jokim.sivillage.api.wishlist.eventwishlist.vo.out.GetAllEventWishlistResponseVo;
import com.jokim.sivillage.api.wishlist.eventwishlist.vo.out.GetEventWishlistStateResponseVo;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class EventWishlistResponseDto {

    private String eventCode;
    private Boolean isChecked;

    public static EventWishlistResponseDto toDto(EventWishlist eventWishlist) {
        return EventWishlistResponseDto.builder()
                .eventCode(eventWishlist.getEventCode())
                .build();
    }

    public static EventWishlistResponseDto toDto(Boolean isChecked) {
        return EventWishlistResponseDto.builder()
                .isChecked(isChecked)
                .build();
    }

    public GetAllEventWishlistResponseVo toVoForEventCode() {
        return GetAllEventWishlistResponseVo.builder()
                .eventCode(eventCode)
                .build();
    }

    public GetEventWishlistStateResponseVo toVoForIsChecked() {
        return GetEventWishlistStateResponseVo.builder()
                .isChecked(isChecked)
                .build();
    }

}
