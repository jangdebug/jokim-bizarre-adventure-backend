package com.jokim.sivillage.api.trending.mostViewProduct.infrastructure;

import com.jokim.sivillage.api.trending.mostViewProduct.domain.MostViewProduct;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MostViewProductRepository extends JpaRepository<MostViewProduct, Long> {
    List<MostViewProduct> findAll();
}
