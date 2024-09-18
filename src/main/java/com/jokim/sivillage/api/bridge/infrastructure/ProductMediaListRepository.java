package com.jokim.sivillage.api.bridge.infrastructure;

import com.jokim.sivillage.api.bridge.domain.ProductMediaList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductMediaListRepository extends JpaRepository<ProductMediaList, Long> {

    List<ProductMediaList> findByProductCode(String productCode);

    boolean existsByProductCode(String productCode);

}
