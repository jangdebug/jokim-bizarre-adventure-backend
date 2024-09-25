package com.jokim.sivillage.api.review.infrastructure;

import com.jokim.sivillage.api.review.domain.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReviewRepository extends JpaRepository<Review,Long> {
    @Query("SELECT r FROM Review r JOIN EvaluationItemValue e ON r.reviewCode = e.reviewCode WHERE r.productCode = :productCode ORDER BY e.isBest DESC")
    Page<Review> findByProductCodeOrderByIsBest(@Param("productCode") String productCode, Pageable pageable);

}
