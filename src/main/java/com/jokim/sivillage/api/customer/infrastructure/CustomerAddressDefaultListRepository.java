package com.jokim.sivillage.api.customer.infrastructure;

import com.jokim.sivillage.api.customer.domain.CustomerAddressDefaultList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerAddressDefaultListRepository extends JpaRepository<CustomerAddressDefaultList, Long> {
    Optional<CustomerAddressDefaultList> findByUuid(String uuid);

}
