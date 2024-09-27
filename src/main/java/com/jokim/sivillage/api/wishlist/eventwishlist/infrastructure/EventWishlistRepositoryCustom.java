package com.jokim.sivillage.api.wishlist.eventwishlist.infrastructure;

import com.jokim.sivillage.api.wishlist.eventwishlist.domain.EventWishlist;
import java.util.List;

public interface EventWishlistRepositoryCustom {

    List<EventWishlist> getAllEventWishlists(String uuid, Integer recentMonths);

}
