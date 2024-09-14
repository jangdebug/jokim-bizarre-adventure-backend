package com.jokim.sivillage.api.customer.infrastructure;

import com.jokim.sivillage.api.customer.domain.Customer;
import com.jokim.sivillage.api.customer.domain.Policy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerPolicyRepository extends JpaRepository<Policy, Long> {
    Optional<Policy> findByUuid(String uuid);
}
