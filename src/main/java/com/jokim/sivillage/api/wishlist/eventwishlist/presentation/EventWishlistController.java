package com.jokim.sivillage.api.wishlist.eventwishlist.presentation;

import com.jokim.sivillage.api.wishlist.eventwishlist.application.EventWishlistService;
import com.jokim.sivillage.api.wishlist.eventwishlist.dto.EventWishlistRequestDto;
import com.jokim.sivillage.api.wishlist.eventwishlist.dto.EventWishlistResponseDto;
import com.jokim.sivillage.api.wishlist.eventwishlist.vo.in.AddEventWishlistRequestVo;
import com.jokim.sivillage.api.wishlist.eventwishlist.vo.out.GetAllEventWishlistResponseVo;
import com.jokim.sivillage.common.entity.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Wishlist")
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/wishlist/event")
public class EventWishlistController {

    private final EventWishlistService eventWishlistService;

    @Operation(summary = "이벤트 Wishlist 생성 API")
    @PostMapping
    public BaseResponse<Void> addEventWishlist(
            @RequestHeader("Authorization") String authorizationHeader,
            @RequestBody AddEventWishlistRequestVo addEventWishlistRequestVo) {

        eventWishlistService.addEventWishlist(EventWishlistRequestDto.toDto(
                addEventWishlistRequestVo, authorizationHeader.replace("Bearer ", "")));
        return new BaseResponse<>();
    }

    @Operation(summary = "이벤트 Wishlist 전체 조회 API")
    @GetMapping
    public BaseResponse<List<GetAllEventWishlistResponseVo>> getAllEventWishlists(
            @RequestHeader("Authorization") String authorizationHeader) {

        return new BaseResponse<>(eventWishlistService.getAllEventWishlists(
                authorizationHeader.replace("Bearer ", ""))
                .stream().map(EventWishlistResponseDto::toVoForEventCode).toList());
    }

}
