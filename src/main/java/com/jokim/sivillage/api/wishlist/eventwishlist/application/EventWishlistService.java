package com.jokim.sivillage.api.wishlist.eventwishlist.application;

import com.jokim.sivillage.api.wishlist.eventwishlist.dto.EventWishlistRequestDto;
import com.jokim.sivillage.api.wishlist.eventwishlist.dto.EventWishlistResponseDto;

import java.util.List;

public interface EventWishlistService {

    void addEventWishlist(EventWishlistRequestDto eventWishlistRequestDto);

    List<EventWishlistResponseDto> getAllEventWishlists(String accessToken);

}
