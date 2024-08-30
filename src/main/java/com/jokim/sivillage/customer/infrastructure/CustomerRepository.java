package com.jokim.sivillage.customer.infrastructure;

import com.jokim.sivillage.customer.domain.Customer;
import com.jokim.sivillage.customer.domain.Marketing;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByEmail(String email);
}


