package com.jokim.sivillage.api.customer.infrastructure;

import com.jokim.sivillage.api.customer.domain.Policy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerPolicyRepository extends JpaRepository<Policy, Long> {

}
