package com.jokim.sivillage.api.wishlist.eventwishlist.dto;

import com.jokim.sivillage.api.wishlist.eventwishlist.domain.EventWishlist;
import com.jokim.sivillage.api.wishlist.eventwishlist.vo.in.AddEventWishlistRequestVo;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class EventWishlistRequestDto {

    private String accessToken;
    private String eventCode;

    public static EventWishlistRequestDto toDto(
            AddEventWishlistRequestVo addEventWishlistRequestVo, String accessToken) {  // add wishlist

        return EventWishlistRequestDto.builder()
                .accessToken(accessToken)
                .eventCode(addEventWishlistRequestVo.getEventCode())
                .build();
    }

    public static EventWishlistRequestDto toDto(String accessToken, String eventCode) {
        return EventWishlistRequestDto.builder()
                .accessToken(accessToken)
                .eventCode(eventCode)
                .build();
    }

    public EventWishlist toEntity(Long id, String uuid, Boolean isChecked) {
        return EventWishlist.builder()
                .id(id)
                .uuid(uuid)
                .eventCode(eventCode)
                .isChecked(isChecked)
                .build();
    }

}
