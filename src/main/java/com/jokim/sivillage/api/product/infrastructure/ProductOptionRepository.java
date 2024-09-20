package com.jokim.sivillage.api.product.infrastructure;

import com.jokim.sivillage.api.product.domain.ProductOption;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductOptionRepository extends JpaRepository<ProductOption, Long> {

}
