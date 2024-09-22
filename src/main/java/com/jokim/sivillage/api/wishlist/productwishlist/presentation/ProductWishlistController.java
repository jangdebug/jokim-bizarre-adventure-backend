package com.jokim.sivillage.api.wishlist.productwishlist.presentation;

import com.jokim.sivillage.api.wishlist.productwishlist.application.ProductWishlistService;
import com.jokim.sivillage.api.wishlist.productwishlist.dto.ProductWishlistRequestDto;
import com.jokim.sivillage.api.wishlist.productwishlist.vo.AddProductWishlistRequestVo;
import com.jokim.sivillage.common.entity.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Wishlist")
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/wishlist/product")
public class ProductWishlistController {

    private final ProductWishlistService productWishlistService;

    @Operation(summary = "상품 Wishlist 생성 API")
    @PostMapping
    public BaseResponse<Void> addProductWishlist(
            @RequestHeader("Authorization") String authorizationHeader,
            @RequestBody AddProductWishlistRequestVo addProductWishlistRequestVo) {

        productWishlistService.addProductWishlist(ProductWishlistRequestDto.toDto(
                addProductWishlistRequestVo, authorizationHeader.replace("Bearer ", "")));
        return new BaseResponse<>();
    }


}
