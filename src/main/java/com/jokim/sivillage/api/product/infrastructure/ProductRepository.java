package com.jokim.sivillage.api.product.infrastructure;


import com.jokim.sivillage.api.product.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findByProductCode(String productCode);

    @Query("SELECT p FROM Product p ORDER BY RAND() LIMIT :count")
    List<Product> getRandomProducts(@Param("count") Integer count);

}
