package com.jokim.sivillage.api.wishlist.productwishlist.application;

import com.jokim.sivillage.api.wishlist.productwishlist.dto.ProductWishlistRequestDto;
import com.jokim.sivillage.api.wishlist.productwishlist.dto.ProductWishlistResponseDto;

import java.util.List;

public interface ProductWishlistService {

    void addProductWishlist(ProductWishlistRequestDto productWishlistRequestDto);

    List<ProductWishlistResponseDto> getAllProductWishlists(String accessToken, Integer recentMonths);
    ProductWishlistResponseDto getProductWishlistState(String accessToken, String productCode);

    void deleteProductWishlist(ProductWishlistRequestDto productWishlistRequestDto);
}
