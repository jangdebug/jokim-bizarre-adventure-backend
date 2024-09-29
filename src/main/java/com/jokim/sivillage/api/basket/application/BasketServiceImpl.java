package com.jokim.sivillage.api.basket.application;

import com.jokim.sivillage.api.basket.domain.Basket;
import com.jokim.sivillage.api.basket.domain.BasketState;
import com.jokim.sivillage.api.basket.dto.in.AddBasketRequestDto;
import com.jokim.sivillage.api.basket.dto.in.DeleteBasketItemRequestDto;
import com.jokim.sivillage.api.basket.dto.in.UpdateBasketRequestDto;
import com.jokim.sivillage.api.basket.dto.out.AllBasketItemsResponseDto;
import com.jokim.sivillage.api.basket.dto.out.BasketItemCountResponseDto;
import com.jokim.sivillage.api.basket.dto.out.ExistsInBasketResponseDto;
import com.jokim.sivillage.api.basket.dto.out.ProductOptionInfoResponseDto;
import com.jokim.sivillage.api.basket.infrastructure.BasketRepository;
import com.jokim.sivillage.api.basket.infrastructure.BasketRepositoryCustom;
import com.jokim.sivillage.api.product.infrastructure.ProductOptionRepository;
import com.jokim.sivillage.common.exception.BaseException;
import com.jokim.sivillage.common.jwt.JwtTokenProvider;
import com.jokim.sivillage.common.utils.CodeGenerator;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.jokim.sivillage.common.entity.BaseResponseStatus.*;

@RequiredArgsConstructor
@Service
public class BasketServiceImpl implements BasketService {

    private final BasketRepository basketRepository;
    private final BasketRepositoryCustom basketRepositoryCustom;
    private final JwtTokenProvider jwtTokenProvider;

    private final ProductOptionRepository productOptionRepository;

    private static final int MAX_CODE_TRIES = 5;

    @Transactional
    @Override
    public void addToBasket(AddBasketRequestDto addBasketRequestDto) {

        if(addBasketRequestDto.getQuantity() <= 0) throw new BaseException(INVALID_PRODUCT_QUANTITY);

        String uuid = jwtTokenProvider.validateAndGetUserUuid(addBasketRequestDto.getAccessToken());
        Basket basket = basketRepository.findByUuidAndProductOptionCodeAndBasketState(uuid,
            addBasketRequestDto.getProductOptionCode(), BasketState.ACTIVE).orElse(new Basket());

        String basketCode = Optional.ofNullable(basket.getBasketCode()).orElse(generateUniqueBasketCode());

        basketRepository.save(addBasketRequestDto.toEntity(basket.getId(), uuid, basketCode, true, "ACTIVE"));
    }

    @Transactional(readOnly = true)
    @Override
    public List<AllBasketItemsResponseDto> getAllBasketItems(String accessToken) {
        return basketRepository.findByUuidAndBasketState(
            jwtTokenProvider.validateAndGetUserUuid(accessToken), BasketState.ACTIVE)
            .stream().map(AllBasketItemsResponseDto::toDto).toList();
    }

    @Transactional(readOnly = true)
    @Override
    public BasketItemCountResponseDto getBasketItemCount(String accessToken) {
        return BasketItemCountResponseDto.toDto(basketRepository.countByUuidAndBasketState(
            jwtTokenProvider.validateAndGetUserUuid(accessToken), BasketState.ACTIVE));

    }

    @Transactional(readOnly = true)
    @Override
    public ExistsInBasketResponseDto existsInBasket(String accessToken, String productOptionCode) {
        return ExistsInBasketResponseDto.toDto(basketRepository.
            existsByUuidAndProductOptionCodeAndBasketState(jwtTokenProvider
                .validateAndGetUserUuid(accessToken), productOptionCode, BasketState.ACTIVE));
    }

    @Transactional(readOnly = true)
    @Override
    public ProductOptionInfoResponseDto getProductOptionInfo(String productOptionCode) {
        return basketRepositoryCustom.getProductOptionInfo(productOptionRepository.findByProductOptionCode(
                productOptionCode).orElseThrow(() -> new BaseException(NOT_EXIST_PRODUCT_OPTION)));
    }

    @Transactional
    @Override
    public void updateBasketItemQuantity(UpdateBasketRequestDto updateBasketRequestDto) {
        if(updateBasketRequestDto.getQuantity() <= 0) throw new BaseException(INVALID_PRODUCT_QUANTITY);

        basketRepository.save(updateBasketRequestDto.toEntityForQuantity(
                basketRepository.findByBasketCodeAndBasketState(updateBasketRequestDto.getBasketCode(),
                                BasketState.ACTIVE).orElseThrow(() -> new BaseException(NOT_EXIST_BASKET_ITEM))));
    }

    @Transactional
    @Override
    public void updateBasketItemCheck(UpdateBasketRequestDto updateBasketRequestDto) {
        basketRepository.save(updateBasketRequestDto.toEntityForCheck(
                basketRepository.findByBasketCodeAndBasketState(updateBasketRequestDto.getBasketCode(),
                        BasketState.ACTIVE).orElseThrow(() -> new BaseException(NOT_EXIST_BASKET_ITEM))));
    }

    @Transactional
    @Override
    public void deleteBasketItems(List<DeleteBasketItemRequestDto> deleteBasketItemRequestDtoList) {
        List<Basket> basketItemList = basketRepository.findByBasketCodeInAndBasketState(
                deleteBasketItemRequestDtoList.stream().map(
                        DeleteBasketItemRequestDto::getBasketCode).toList(), BasketState.ACTIVE);

        basketRepository.saveAll(basketItemList.stream().map(item -> {      // Soft Delete
                    DeleteBasketItemRequestDto dto = deleteBasketItemRequestDtoList.stream()
                            .filter(requestDto -> requestDto.getBasketCode().equals(item.getBasketCode()))
                            .findFirst()
                            .orElseThrow(() -> new BaseException(NOT_EXIST_BASKET_ITEM));

                    return dto.toEntity(item);
                }
                ).toList());
    }

    private String generateUniqueBasketCode() {
        for(int i = 0; i < MAX_CODE_TRIES; i++) {
            String basketCode = CodeGenerator.generateCode("BK");

            if(!basketRepository.existsByBasketCode(basketCode)) return basketCode;
        }

        throw new BaseException(FAILED_TO_GENERATE_BASKET_CODE);
    }

}
