package com.jokim.sivillage.api.brand.infrastructure;

import com.jokim.sivillage.api.brand.domain.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<Brand, Long> {

    boolean existsByBrandCode(String brandCode);

}
