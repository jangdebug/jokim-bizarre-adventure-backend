package com.jokim.sivillage.api.customer.infrastructure;

import com.jokim.sivillage.api.customer.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findByEmail(String email);

    Optional<Customer> findByUuid(String uuid);

}


