package com.jokim.sivillage.api.bridge.infrastructure;

import com.jokim.sivillage.api.bridge.domain.ProductCategoryList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductCategoryListRepository extends JpaRepository<ProductCategoryList, Long> {
}
