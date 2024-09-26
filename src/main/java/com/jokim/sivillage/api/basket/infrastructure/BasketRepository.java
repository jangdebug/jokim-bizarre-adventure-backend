package com.jokim.sivillage.api.basket.infrastructure;

import com.jokim.sivillage.api.basket.domain.Basket;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BasketRepository extends JpaRepository<Basket, Long> {

    Optional<Basket> findByUuidAndProductOptionCode(String uuid, String productOptionCode);

    boolean existsByBasketCode(String basketCode);

}
