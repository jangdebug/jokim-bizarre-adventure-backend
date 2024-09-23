package com.jokim.sivillage.api.product.infrastructure;


import com.jokim.sivillage.api.product.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findById(Long id);

    Optional<Product> findByProductCode(String productCode);
//    @Query("SELECT p FROM Product p, ProductOption po JOIN p.options po WHERE po.size.id = :sizeValue AND po.color.id = :colorValue AND po.etc.id = :etcValue")
//    List<Product> findBySizeAndColorAndEtc(@Param("sizeValue") Long sizeValue,
//        @Param("colorValue") Long colorValue, @Param("etcValue") Long etcValue);

    @Query("SELECT p FROM Product p ORDER BY RAND() LIMIT :count")
    List<Product> findRandomProducts(@Param("count") Integer count);

//    List<Product> findProductByHit();

//    List<Product> findProductByNewProduct();

//    List<Product> findProductByReview();
//
//    List<Product> findProductBySalesVolume();
//
//    List<Product> findProductByDiscountRate();
//
//    List<Product> findProductByHighPrice();
//
//    List<Product> findProductByWish();


}
