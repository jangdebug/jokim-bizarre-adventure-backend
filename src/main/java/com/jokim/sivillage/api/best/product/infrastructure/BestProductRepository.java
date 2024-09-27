package com.jokim.sivillage.api.best.product.infrastructure;

import com.jokim.sivillage.api.best.product.domain.BestProduct;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BestProductRepository extends JpaRepository<BestProduct, Long> {
    List<BestProduct> findAll();
}
