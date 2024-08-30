package com.jokim.sivillage.customer.infrastructure;

import com.jokim.sivillage.customer.domain.Marketing;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerMarketingRepository extends JpaRepository<Marketing,Long> {
}
