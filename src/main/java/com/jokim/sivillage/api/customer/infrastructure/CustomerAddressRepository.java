package com.jokim.sivillage.api.customer.infrastructure;

import com.jokim.sivillage.api.customer.domain.Address;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerAddressRepository extends JpaRepository<Address, Long> {
    void deleteByAddressCode(String addressCode);
    Optional<Address> findByAddressCode(String addressCode);
    List<Address> findByUuid(String uuid);
}
