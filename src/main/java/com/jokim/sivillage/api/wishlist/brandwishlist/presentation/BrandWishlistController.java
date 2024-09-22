package com.jokim.sivillage.api.wishlist.brandwishlist.presentation;

import com.jokim.sivillage.api.wishlist.brandwishlist.application.BrandWishlistService;
import com.jokim.sivillage.api.wishlist.brandwishlist.dto.BrandWishlistRequestDto;
import com.jokim.sivillage.api.wishlist.brandwishlist.vo.in.AddBrandWishlistRequestVo;
import com.jokim.sivillage.common.entity.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static com.jokim.sivillage.common.utils.TokenExtractor.extractToken;

@Tag(name = "Wishlist")
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

}
