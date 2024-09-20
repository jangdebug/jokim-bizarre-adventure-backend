package com.jokim.sivillage.api.review.infrastructure;

import com.jokim.sivillage.api.review.domain.EvaluationItemName;
import com.jokim.sivillage.api.review.domain.ProductEvaluationManage;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductEvaluationManageRepository extends JpaRepository<ProductEvaluationManage,Long> {
    List<ProductEvaluationManage> findByProductCode(String productCode);

}
