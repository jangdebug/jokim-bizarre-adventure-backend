package com.jokim.sivillage.api.wishlist.brandwishlist.application;

import com.jokim.sivillage.api.wishlist.brandwishlist.dto.BrandWishlistRequestDto;
import com.jokim.sivillage.api.wishlist.brandwishlist.dto.BrandWishlistResponseDto;

import java.util.List;

public interface BrandWishlistService {

    void addBrandWishlist(BrandWishlistRequestDto brandWishlistRequestDto);

    List<BrandWishlistResponseDto> getAllBrandWishlists(String accessToken);
    BrandWishlistResponseDto getBrandWishlistState(String accessToken, String brandCode);

    void deleteBrandWishlist(BrandWishlistRequestDto brandWishlistRequestDto);

}
