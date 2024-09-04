package com.jokim.sivillage.api.customer.infrastructure;

import com.jokim.sivillage.api.customer.domain.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerAdressRepository extends JpaRepository<Address, Long> {

}
