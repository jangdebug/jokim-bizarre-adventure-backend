package com.jokim.sivillage.api.review.infrastructure;

import com.jokim.sivillage.api.review.domain.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review,Long> {
    Page<Review> findByProductCode(String productCode, Pageable pageable);

}
