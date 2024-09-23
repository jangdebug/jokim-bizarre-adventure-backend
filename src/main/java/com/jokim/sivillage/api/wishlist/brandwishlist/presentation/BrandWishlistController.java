package com.jokim.sivillage.api.wishlist.brandwishlist.presentation;

import com.jokim.sivillage.api.wishlist.brandwishlist.application.BrandWishlistService;
import com.jokim.sivillage.api.wishlist.brandwishlist.dto.BrandWishlistRequestDto;
import com.jokim.sivillage.api.wishlist.brandwishlist.dto.BrandWishlistResponseDto;
import com.jokim.sivillage.api.wishlist.brandwishlist.vo.in.AddBrandWishlistRequestVo;
import com.jokim.sivillage.api.wishlist.brandwishlist.vo.out.GetAllBrandWishlistResponseVo;
import com.jokim.sivillage.api.wishlist.brandwishlist.vo.out.GetBrandWishlistStateResponseVo;
import com.jokim.sivillage.common.entity.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.jokim.sivillage.common.utils.TokenExtractor.extractToken;

@Tag(name = "Wishlist-Brand")
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/wishlist/brand")
public class BrandWishlistController {

    private final BrandWishlistService brandWishlistService;

    @Operation(summary = "Brand Wishlist 생성 API")
    @PostMapping
    public BaseResponse<Void> addBrandWishlist(
            @RequestHeader("Authorization") String authorizationHeader,
            @RequestBody AddBrandWishlistRequestVo addBrandWishlistRequestVo) {

        brandWishlistService.addBrandWishlist(BrandWishlistRequestDto.toDto(
                addBrandWishlistRequestVo, extractToken(authorizationHeader)));
        return new BaseResponse<>();
    }

    @Operation(summary = "Brand Wishlist 전체 조회 API")
    @GetMapping
    public BaseResponse<List<GetAllBrandWishlistResponseVo>>  getAllBrandWishlists(
            @RequestHeader("Authorization") String authorizationHeader) {

        return new BaseResponse<>(brandWishlistService.getAllBrandWishlists(extractToken(authorizationHeader))
                .stream().map(BrandWishlistResponseDto::toVoForBrandCode).toList());
    }

    @Operation(summary = "Brand Wishlist 상태 조회 API")
    @GetMapping("/{brandCode}")
    public BaseResponse<GetBrandWishlistStateResponseVo> getBrandWishlistState(
            @RequestHeader("Authorization") String authorizationHeader, @PathVariable String brandCode) {

        return new BaseResponse<>(brandWishlistService.getBrandWishlistState(
                extractToken(authorizationHeader), brandCode).toVoForIsChecked());
    }

    @Operation(summary = "Brand Wishlist 삭제 API")
    @DeleteMapping("/{brandCode}")
    public BaseResponse<Void> deleteBrandWishlist(
            @RequestHeader("Authorization") String authorizationHeader, @PathVariable String brandCode) {

        brandWishlistService.deleteBrandWishlist(BrandWishlistRequestDto.toDto(
                extractToken(authorizationHeader), brandCode));
        return new BaseResponse<>();
    }

}
