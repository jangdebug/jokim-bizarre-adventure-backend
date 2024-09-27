package com.jokim.sivillage.api.review.infrastructure;

import com.jokim.sivillage.api.review.domain.ProductStarAverage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductStarAverageRepository extends JpaRepository<ProductStarAverage, Long> {
    ProductStarAverage findByProductCode(String productCode);

}
