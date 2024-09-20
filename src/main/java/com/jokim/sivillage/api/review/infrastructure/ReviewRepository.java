package com.jokim.sivillage.api.review.infrastructure;

import com.jokim.sivillage.api.review.domain.Review;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review,Long> {
    List<Review> findByProductCode(String productCode);

}
