package com.jokim.sivillage.api.wishlist.productwishlist.presentation;

import com.jokim.sivillage.api.wishlist.productwishlist.application.ProductWishlistService;
import com.jokim.sivillage.api.wishlist.productwishlist.dto.ProductWishlistRequestDto;
import com.jokim.sivillage.api.wishlist.productwishlist.dto.ProductWishlistResponseDto;
import com.jokim.sivillage.api.wishlist.productwishlist.vo.in.AddProductWishlistRequestVo;
import com.jokim.sivillage.api.wishlist.productwishlist.vo.out.GetAllProductWishlistResponseVo;
import com.jokim.sivillage.api.wishlist.productwishlist.vo.out.GetProductWishlistStateResponseVo;
import com.jokim.sivillage.common.entity.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.jokim.sivillage.common.utils.TokenExtractor.extractToken;

@Tag(name = "Wishlist-Product")
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
                addProductWishlistRequestVo, extractToken(authorizationHeader)));
        return new BaseResponse<>();
    }

    @Operation(summary = "상품 Wishlist 전체 조회 API", description =
        "recentMonths로 최근 N 개월 동안 찜한 것들만 필터링")
    @GetMapping
    public BaseResponse<List<GetAllProductWishlistResponseVo>> getAllProductWishlists(
            @RequestHeader("Authorization") String authorizationHeader,
            @RequestParam(value = "recentMonths", required = false) Integer recentMonths) {

        return new BaseResponse<>(productWishlistService.getAllProductWishlists(
            extractToken(authorizationHeader), recentMonths).stream()
            .map(ProductWishlistResponseDto::toVoForProductCode).toList());
    }

    @Operation(summary = "상품 Wishlist 상태 조회 API")
    @GetMapping("/{productCode}")
    public BaseResponse<GetProductWishlistStateResponseVo> getProductWishlistState(
            @RequestHeader("Authorization") String authorizationHeader, @PathVariable String productCode) {

        return new BaseResponse<>(productWishlistService.getProductWishlistState(
                extractToken(authorizationHeader), productCode).toVoForIsChecked());
    }

    @Operation(summary = "상품 Wishlist 삭제 API", description = "Soft Delete")
    @DeleteMapping("/{productCode}")
    public BaseResponse<Void> deleteProductWishlist(
            @RequestHeader("Authorization") String authorizationHeader, @PathVariable String productCode) {

        productWishlistService.deleteProductWishlist(ProductWishlistRequestDto.toDto(
                extractToken(authorizationHeader), productCode));
        return new BaseResponse<>();
    }

}
