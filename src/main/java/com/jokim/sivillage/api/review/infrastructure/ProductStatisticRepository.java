package com.jokim.sivillage.api.review.infrastructure;

import com.jokim.sivillage.api.review.domain.ProductStatistic;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductStatisticRepository extends JpaRepository<ProductStatistic, Long> {
    List<ProductStatistic> findByProductCode(String productCode);
}
