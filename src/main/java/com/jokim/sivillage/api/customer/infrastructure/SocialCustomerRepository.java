package com.jokim.sivillage.api.customer.infrastructure;

import com.jokim.sivillage.api.customer.domain.SocialCustomer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SocialCustomerRepository extends JpaRepository<SocialCustomer,Long> {

    Optional<SocialCustomer> findByUuidAndOauthProviderId(String uuid, String OauthProviderId);

}
