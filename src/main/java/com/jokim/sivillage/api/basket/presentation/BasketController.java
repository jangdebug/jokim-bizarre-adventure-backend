package com.jokim.sivillage.api.basket.presentation;

import static com.jokim.sivillage.common.utils.TokenExtractor.extractToken;

import com.jokim.sivillage.api.basket.application.BasketService;
import com.jokim.sivillage.api.basket.dto.BasketRequestDto;
import com.jokim.sivillage.api.basket.vo.in.AddToBasketRequestVo;
import com.jokim.sivillage.common.entity.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
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

    @Operation(summary = "장바구니에 상품 담기")
    @PostMapping
    public BaseResponse<Void> addToBasket(
        @RequestHeader("Authorization") String authorizationHeader,
        @RequestBody AddToBasketRequestVo addToBasketRequestVo) {

        basketService.addToBasket(BasketRequestDto.toDto(addToBasketRequestVo,
            extractToken(authorizationHeader)));
        return new BaseResponse<>();
    }

}
