package com.jokim.sivillage.api.brand.infrastructure;

import com.jokim.sivillage.api.brand.domain.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BrandRepository extends JpaRepository<Brand, Long> {

    List<Brand> findAllByKoreanNameIsNotNullOrderByEnglishInitial();

    Optional<Brand> findByBrandCode(String brandCode);

    boolean existsByBrandCode(String brandCode);

    void deleteByBrandCode(String brandCode);

}
