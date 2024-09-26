package com.jokim.sivillage.api.review.infrastructure;

import com.jokim.sivillage.api.review.domain.Report;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository extends JpaRepository<Report,Long> {
}
