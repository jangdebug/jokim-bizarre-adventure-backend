package com.jokim.sivillage.api.customer.infrastructure;

import com.jokim.sivillage.api.customer.domain.Marketing;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerMarketingRepository extends JpaRepository<Marketing, Long> {

}
