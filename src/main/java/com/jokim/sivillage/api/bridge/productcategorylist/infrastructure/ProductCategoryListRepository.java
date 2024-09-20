package com.jokim.sivillage.api.bridge.productcategorylist.infrastructure;

import com.jokim.sivillage.api.bridge.productcategorylist.domain.ProductCategoryList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductCategoryListRepository extends JpaRepository<ProductCategoryList, Long> {
}
