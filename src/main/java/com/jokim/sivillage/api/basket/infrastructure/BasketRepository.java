package com.jokim.sivillage.api.basket.infrastructure;

import com.jokim.sivillage.api.basket.domain.Basket;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BasketRepository extends JpaRepository<Basket, Long> {

    Optional<Basket> findByUuidAndProductOptionCodeAndIsChecked(String uuid,
        String productOptionCode, Boolean isChecked);

    List<Basket> findByUuidAndIsChecked(String uuid, Boolean isChecked);
    int countByUuidAndIsChecked(String uuid, Boolean isChecked);
    boolean existsByUuidAndProductOptionCodeAndIsChecked(String uuid, String productOptionCode,
        Boolean isChecked);

    boolean existsByBasketCode(String basketCode);

}
