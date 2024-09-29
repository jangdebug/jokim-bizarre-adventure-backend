package com.jokim.sivillage.api.product.infrastructure;

import com.jokim.sivillage.api.product.domain.ProductOption;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductOptionRepository extends JpaRepository<ProductOption, Long> {

    Optional<ProductOption> findByProductOptionCode(String productOptionCode);

}
