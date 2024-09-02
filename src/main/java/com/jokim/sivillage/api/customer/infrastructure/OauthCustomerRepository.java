package com.jokim.sivillage.api.customer.infrastructure;

import com.jokim.sivillage.api.customer.domain.OauthCustomer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OauthCustomerRepository extends JpaRepository<OauthCustomer,Long> {
    Optional<OauthCustomer> findByLoginId(String loginId);
}
