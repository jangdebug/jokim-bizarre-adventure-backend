package com.jokim.sivillage.api.customer.infrastructure;

import com.jokim.sivillage.api.customer.domain.CustomerSize;
import com.jokim.sivillage.api.customer.domain.Marketing;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerSizeRepository extends JpaRepository<CustomerSize, Long> {
    Optional<CustomerSize> findByUuid(String uuid);
}
