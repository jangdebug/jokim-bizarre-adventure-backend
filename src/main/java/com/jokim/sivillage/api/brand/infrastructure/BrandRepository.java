package com.jokim.sivillage.api.brand.infrastructure;

import com.jokim.sivillage.api.brand.domain.Brand;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<Brand, Long> {


    Optional<Brand> findByMainName(String mainName);

    Optional<Brand> findByBrandCode(String brandCode);
}
