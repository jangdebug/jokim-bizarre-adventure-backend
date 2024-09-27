package com.jokim.sivillage.api.review.infrastructure;

import com.jokim.sivillage.api.review.domain.Review;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReviewRepository extends JpaRepository<Review,Long> {
    Page<Review> findByProductCode(String productCode, Pageable pageable);
    @Query("SELECT COUNT(r) FROM Review r WHERE r.productCode = :productCode")
    Integer countByProductCode(@Param("productCode") String productCode);

    @Query("SELECT COUNT(r) FROM Review r WHERE r.uuid = :uuid")
    Integer countByUuid(@Param("uuid") String uuid);

    List<Review> findByUuid(String uuid);
}
