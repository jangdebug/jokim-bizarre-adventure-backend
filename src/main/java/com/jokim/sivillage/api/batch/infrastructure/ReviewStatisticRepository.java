package com.jokim.sivillage.api.batch.infrastructure;

import com.jokim.sivillage.api.batch.domain.ReviewStatistic;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewStatisticRepository extends JpaRepository<ReviewStatistic, Long> {
    ReviewStatistic findByReviewCode(String reviewCode);
}
