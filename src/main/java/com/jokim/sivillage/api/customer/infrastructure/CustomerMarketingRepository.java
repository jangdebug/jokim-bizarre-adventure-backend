package com.jokim.sivillage.api.customer.infrastructure;

import com.jokim.sivillage.api.customer.domain.Customer;
import com.jokim.sivillage.api.customer.domain.Marketing;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerMarketingRepository extends JpaRepository<Marketing, Long> {
    Optional<Marketing> findByUuid(String uuid);
}
