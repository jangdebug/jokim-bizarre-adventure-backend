package com.jokim.sivillage.api.wishlist.productwishlist.infrastructure;

import com.jokim.sivillage.api.wishlist.productwishlist.domain.ProductWishlist;
import java.util.List;

public interface ProductWishlistRepositoryCustom {

    List<ProductWishlist> getAllProductWishlists(String uuid, Integer recentMonths);

}
