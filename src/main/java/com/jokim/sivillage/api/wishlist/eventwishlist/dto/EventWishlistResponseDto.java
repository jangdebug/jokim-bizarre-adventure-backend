package com.jokim.sivillage.api.wishlist.eventwishlist.dto;

import com.jokim.sivillage.api.wishlist.eventwishlist.domain.EventWishlist;
import com.jokim.sivillage.api.wishlist.eventwishlist.vo.out.GetAllEventWishlistResponseVo;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class EventWishlistResponseDto {

    private String eventCode;

    public static EventWishlistResponseDto toDto(EventWishlist eventWishlist) {
        return EventWishlistResponseDto.builder()
                .eventCode(eventWishlist.getEventCode())
                .build();
    }

    public GetAllEventWishlistResponseVo toVoForEventCode() {
        return GetAllEventWishlistResponseVo.builder()
                .eventCode(eventCode)
                .build();
    }

}
