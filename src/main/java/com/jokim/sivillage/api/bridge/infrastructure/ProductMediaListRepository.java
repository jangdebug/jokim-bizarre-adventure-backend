package com.jokim.sivillage.api.bridge.infrastructure;

import com.jokim.sivillage.api.bridge.domain.ProductMediaList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductMediaListRepository extends JpaRepository<ProductMediaList, Long> {

    Optional<ProductMediaList> findByProductCodeAndMediaCode(String productCode, String mediaCode);

    List<ProductMediaList> findByProductCodeAndIsThumbnail(String productCode, Boolean isThumbnail);
    List<ProductMediaList> findByProductCode(String productCode);

    boolean existsByProductCode(String productCode);

}
