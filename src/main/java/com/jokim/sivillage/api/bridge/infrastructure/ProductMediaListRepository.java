package com.jokim.sivillage.api.bridge.infrastructure;

import com.jokim.sivillage.api.bridge.domain.ProductMediaList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductMediaListRepository extends JpaRepository<ProductMediaList, Long> {
}
