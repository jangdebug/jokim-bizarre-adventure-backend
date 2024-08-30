package com.jokim.sivillage.customer.infrastructure;

import com.jokim.sivillage.customer.domain.DefaultAddress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerDefaultAddresRepository extends JpaRepository<DefaultAddress, Long> {
}
