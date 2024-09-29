package com.jokim.sivillage.api.basket.infrastructure;

import com.jokim.sivillage.api.basket.domain.Basket;
import java.util.List;
import java.util.Optional;

import com.jokim.sivillage.api.basket.domain.BasketState;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BasketRepository extends JpaRepository<Basket, Long> {

    Optional<Basket> findByUuidAndProductOptionCodeAndBasketState(String uuid,
        String productOptionCode, BasketState basketState);
    Optional<Basket> findByBasketCodeAndBasketState(String basketCode, BasketState basketState);

    List<Basket> findByBasketCodeInAndBasketState(List<String> basketCodeList, BasketState basketState);

    List<Basket> findByUuidAndBasketState(String uuid, BasketState basketState);
    int countByUuidAndBasketState(String uuid, BasketState basketState);

    boolean existsByUuidAndProductOptionCodeAndBasketState(String uuid, String productOptionCode, BasketState basketState);
    boolean existsByBasketCode(String basketCode);

}
