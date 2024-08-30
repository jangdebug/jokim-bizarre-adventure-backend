package com.jokim.sivillage.customer.infrastructure;

import com.jokim.sivillage.customer.domain.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerAdressRepository extends JpaRepository<Address, Long> {
}
