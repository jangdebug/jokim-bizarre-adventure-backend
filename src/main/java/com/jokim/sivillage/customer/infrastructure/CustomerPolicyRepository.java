package com.jokim.sivillage.customer.infrastructure;

import com.jokim.sivillage.customer.domain.Policy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerPolicyRepository extends JpaRepository<Policy, Long> {
}
