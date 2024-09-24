package com.jokim.sivillage.api.bridge.brandmedialist.infrastructure;

import com.jokim.sivillage.api.bridge.brandmedialist.domain.BrandMediaList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandMediaListRepository extends JpaRepository<BrandMediaList, Long> {

    Optional<BrandMediaList> findByBrandCodeAndMediaCode(String brandCode, String mediaCode);

    List<BrandMediaList> findByBrandCodeAndIsLogo(String brandCode, Boolean isLogo);

    boolean existsByBrandCodeAndIsLogo(String brandCode, Boolean isLogo);

}
