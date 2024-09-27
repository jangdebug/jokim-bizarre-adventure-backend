package com.jokim.sivillage.api.basket.presentation;

import static com.jokim.sivillage.common.utils.TokenExtractor.extractToken;

import com.jokim.sivillage.api.basket.application.BasketService;
import com.jokim.sivillage.api.basket.dto.in.BasketRequestDto;
import com.jokim.sivillage.api.basket.dto.out.AllBasketItemsResponseDto;
import com.jokim.sivillage.api.basket.vo.in.AddToBasketRequestVo;
import com.jokim.sivillage.api.basket.vo.out.GetAllBasketItemsResponseVo;
import com.jokim.sivillage.api.basket.vo.out.GetBasketItemCountResponseVo;
import com.jokim.sivillage.api.basket.vo.out.GetExistsInBasketResponseVo;
import com.jokim.sivillage.common.entity.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Basket")
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/basket")
public class BasketController {

    private final BasketService basketService;

    @Operation(summary = "장바구니에 상품 담기 API")
    @PostMapping
    public BaseResponse<Void> addToBasket(
        @RequestHeader("Authorization") String authorizationHeader,
        @RequestBody AddToBasketRequestVo addToBasketRequestVo) {

        basketService.addToBasket(BasketRequestDto.toDto(addToBasketRequestVo,
            extractToken(authorizationHeader)));
        return new BaseResponse<>();
    }

    @Operation(summary = "장바구니 상품 전체 조회 API")
    @GetMapping
    public BaseResponse<List<GetAllBasketItemsResponseVo>> getAllBasketItems(
        @RequestHeader("Authorization") String authorizationHeader) {

        return new BaseResponse<>(basketService.getAllBasketItems(extractToken(authorizationHeader))
            .stream().map(AllBasketItemsResponseDto::toVo).toList());
    }

    @Operation(summary = "장바구니 상품 리스트 개수 조회 API")
    @GetMapping("/count")
    public BaseResponse<GetBasketItemCountResponseVo> getBasketItemCount(
        @RequestHeader("Authorization") String authorizationHeader) {

        return new BaseResponse<>(basketService.getBasketItemCount(extractToken(
            authorizationHeader)).toVo());
    }

    @Operation(summary = "상품의 장바구니 존재 여부 조회 API")
    @GetMapping("/{productOptionCode}")
    public BaseResponse<GetExistsInBasketResponseVo> existsInBasket(
        @RequestHeader("Authorization") String authorizationHeader,
        @PathVariable String productOptionCode) {

        return new BaseResponse<>(basketService.existsInBasket(extractToken(
            authorizationHeader), productOptionCode).toVo());
    }

}
