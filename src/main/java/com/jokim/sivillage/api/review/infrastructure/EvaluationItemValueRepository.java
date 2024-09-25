package com.jokim.sivillage.api.review.infrastructure;

import com.jokim.sivillage.api.review.domain.EvaluationItemValue;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EvaluationItemValueRepository  extends JpaRepository<EvaluationItemValue,Long> {
    List<EvaluationItemValue> findByReviewCode(String reviewCode);
}
