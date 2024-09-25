package com.jokim.sivillage.api.review.infrastructure;

import com.jokim.sivillage.api.review.domain.ProductEvaluationManage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductEvaluationManageRepository extends JpaRepository<ProductEvaluationManage,Long> {

}
