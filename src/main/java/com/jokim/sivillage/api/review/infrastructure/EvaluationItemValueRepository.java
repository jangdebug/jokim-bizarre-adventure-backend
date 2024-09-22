package com.jokim.sivillage.api.review.infrastructure;

import com.jokim.sivillage.api.review.domain.EvaluationItemValue;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EvaluationItemValueRepository  extends JpaRepository<EvaluationItemValue,Long> {
    List<EvaluationItemValue> findByEvaluationItemNameId(Long id);
    List<EvaluationItemValue> findByEvaluationItemNameIdIn(List<Long> evaluationItemNameIds);
    Optional<EvaluationItemValue> findFirstByEvaluationItemNameId(Long evaluationItemNameId);
}
