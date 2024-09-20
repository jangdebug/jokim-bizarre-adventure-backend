package com.jokim.sivillage.api.bridge.brandproductlist.repository;

import com.jokim.sivillage.api.bridge.brandproductlist.domain.BrandProductList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandProductListRepository extends JpaRepository<BrandProductList, Long> {

    BrandProductList findBrandProductListByProductCode(String productCode);

}
