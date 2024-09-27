package com.jokim.sivillage.api.basket.application;

import static com.jokim.sivillage.common.entity.BaseResponseStatus.FAILED_TO_GENERATE_BASKET_CODE;

import com.jokim.sivillage.api.basket.domain.Basket;
import com.jokim.sivillage.api.basket.dto.in.BasketRequestDto;
import com.jokim.sivillage.api.basket.dto.out.AllBasketItemsResponseDto;
import com.jokim.sivillage.api.basket.dto.out.BasketItemCountResponseDto;
import com.jokim.sivillage.api.basket.infrastructure.BasketRepository;
import com.jokim.sivillage.common.exception.BaseException;
import com.jokim.sivillage.common.jwt.JwtTokenProvider;
import com.jokim.sivillage.common.utils.CodeGenerator;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class BasketServiceImpl implements BasketService {

    private final BasketRepository basketRepository;
    private final JwtTokenProvider jwtTokenProvider;

    private static final int MAX_CODE_TRIES = 5;

    @Transactional
    @Override
    public void addToBasket(BasketRequestDto basketRequestDto) {

        String uuid = jwtTokenProvider.validateAndGetUserUuid(basketRequestDto.getAccessToken());
        Basket basket = basketRepository.findByUuidAndProductOptionCodeAndIsChecked(uuid,
            basketRequestDto.getProductOptionCode(), true).orElse(new Basket());

        String basketCode = Optional.ofNullable(basket.getBasketCode()).orElse(generateUniqueBasketCode());

        basketRepository.save(basketRequestDto.toEntity(basket.getId(), uuid, basketCode, true, "ACTIVE"));
    }

    @Override
    public List<AllBasketItemsResponseDto> getAllBasketItems(String accessToken) {
        return basketRepository.findByUuidAndIsChecked(
            jwtTokenProvider.validateAndGetUserUuid(accessToken), true)
            .stream().map(AllBasketItemsResponseDto::toDto).toList();
    }

    @Override
    public BasketItemCountResponseDto getBasketItemCount(String accessToken) {
        return BasketItemCountResponseDto.toDto(basketRepository.countByUuidAndIsChecked(
            jwtTokenProvider.validateAndGetUserUuid(accessToken), true));

    }


    private String generateUniqueBasketCode() {
        for(int i = 0; i < MAX_CODE_TRIES; i++) {
            String basketCode = CodeGenerator.generateCode("BK");

            if(!basketRepository.existsByBasketCode(basketCode)) return basketCode;
        }

        throw new BaseException(FAILED_TO_GENERATE_BASKET_CODE);
    }

}
