package com.jokim.sivillage.api.customer.infrastructure;

import com.jokim.sivillage.api.customer.domain.CustomerAddressDefaultList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerDefaultAddresRepository extends JpaRepository<CustomerAddressDefaultList, Long> {

}
