package com.jokim.sivillage.api.wishlist.eventwishlist.presentation;

import com.jokim.sivillage.api.wishlist.eventwishlist.application.EventWishlistService;
import com.jokim.sivillage.api.wishlist.eventwishlist.dto.EventWishlistRequestDto;
import com.jokim.sivillage.api.wishlist.eventwishlist.dto.EventWishlistResponseDto;
import com.jokim.sivillage.api.wishlist.eventwishlist.vo.in.AddEventWishlistRequestVo;
import com.jokim.sivillage.api.wishlist.eventwishlist.vo.out.GetAllEventWishlistResponseVo;
import com.jokim.sivillage.api.wishlist.eventwishlist.vo.out.GetEventWishlistStateResponseVo;
import com.jokim.sivillage.common.entity.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.jokim.sivillage.common.utils.TokenExtractor.extractToken;

@Tag(name = "Wishlist-Event")
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
                addEventWishlistRequestVo, extractToken(authorizationHeader)));
        return new BaseResponse<>();
    }

    @Operation(summary = "이벤트 Wishlist 전체 조회 API", description =
        "recentMonths로 최근 N 개월 동안 찜한 것들만 필터링")
    @GetMapping
    public BaseResponse<List<GetAllEventWishlistResponseVo>> getAllEventWishlists(
            @RequestHeader("Authorization") String authorizationHeader,
            @RequestParam(value = "recentMonths", required = false) Integer recentMonths) {

        return new BaseResponse<>(eventWishlistService.getAllEventWishlists(
            extractToken(authorizationHeader), recentMonths)
                .stream().map(EventWishlistResponseDto::toVoForEventCode).toList());
    }

    @Operation(summary = "이벤트 Wishlist 상태 조회 API")
    @GetMapping("/{eventCode}")
    public BaseResponse<GetEventWishlistStateResponseVo> getEventWishlistState(
            @RequestHeader("Authorization") String authorizationHeader, @PathVariable String eventCode) {

        return new BaseResponse<>(eventWishlistService.getEventWishlistState(
                extractToken(authorizationHeader), eventCode).toVoForIsChecked());
    }

    @Operation(summary = "이벤트 Wishlist 삭제 API", description = "Soft Delete")
    @DeleteMapping("/{eventCode}")
    public BaseResponse<Void> deleteEventWishlist(
            @RequestHeader("Authorization") String authorizationHeader, @PathVariable String eventCode) {

        eventWishlistService.deleteEventWishlist(EventWishlistRequestDto.toDto(
                extractToken(authorizationHeader), eventCode));
        return new BaseResponse<>();
    }

}
