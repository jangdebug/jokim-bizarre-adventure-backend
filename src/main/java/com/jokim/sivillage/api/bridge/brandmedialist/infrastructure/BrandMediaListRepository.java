package com.jokim.sivillage.api.bridge.brandmedialist.infrastructure;

import com.jokim.sivillage.api.bridge.brandmedialist.domain.BrandMediaList;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandMediaListRepository extends JpaRepository<BrandMediaList, Long> {

    List<BrandMediaList> findByBrandCode(String brandCode);

    boolean existsByBrandCodeAndIsLogo(String brandCode, Boolean isLogo);

}
