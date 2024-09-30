package com.jokim.sivillage.api.mostViewProduct.infrastructure;

import com.jokim.sivillage.api.mostViewProduct.domain.MostViewProduct;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MostViewProductRepository extends JpaRepository<MostViewProduct, Long> {
    List<MostViewProduct> findAll();
}
