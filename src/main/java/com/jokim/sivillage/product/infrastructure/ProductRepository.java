package com.jokim.sivillage.product.infrastructure;

import com.jokim.sivillage.product.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findById(Long id);

    // Product 재활용 하기
//    @Query("SELECT p FROM Product p")
    @Query("SELECT p FROM Product p JOIN p.options po WHERE po.size.id = :sizeValue AND po.color.id = :colorValue AND po.etc.id = :etcValue")
    List<Product> findBySizeAndColorAndEtc(@Param("sizeValue") Long sizeValue, @Param("colorValue") Long colorValue, @Param("etcValue")Long etcValue);
    @Query("SELECT p FROM Product p ORDER BY RAND() LIMIT :count")
    List<Product> findRandomProducts(@Param("count") Integer count);
}
