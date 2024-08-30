package com.jokim.sivillage.api.customer.infrastructure;

import com.jokim.sivillage.api.customer.domain.DefaultAddress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerDefaultAddresRepository extends JpaRepository<DefaultAddress, Long> {
}
