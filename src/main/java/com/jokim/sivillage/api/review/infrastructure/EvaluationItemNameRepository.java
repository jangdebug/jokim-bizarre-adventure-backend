package com.jokim.sivillage.api.review.infrastructure;

import com.jokim.sivillage.api.review.domain.EvaluationItemName;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EvaluationItemNameRepository  extends JpaRepository<EvaluationItemName,Long> {
}
