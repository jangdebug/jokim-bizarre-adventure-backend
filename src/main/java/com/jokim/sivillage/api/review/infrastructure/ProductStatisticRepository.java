package com.jokim.sivillage.api.review.infrastructure;

import com.jokim.sivillage.api.review.domain.ProductStatistic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductStatisticRepository extends JpaRepository<ProductStatistic, Long> {
    ProductStatistic findByProductCode(String productCode);
}
