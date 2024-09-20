package com.jokim.sivillage.api.customer.infrastructure;

import com.jokim.sivillage.api.customer.domain.CustomerAddressDefaultList;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerAddressDefaultListRepository extends JpaRepository<CustomerAddressDefaultList, Long> {
    List<CustomerAddressDefaultList> findByUuid(String uuid);
    Optional<CustomerAddressDefaultList> findByAddressCode(String addressCode);
    Optional<CustomerAddressDefaultList> findByUuidAndIsDefault(String uuid, Boolean isDefault);
    void deleteByAddressCode(String addressCode);
}
