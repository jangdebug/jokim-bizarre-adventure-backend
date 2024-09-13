package com.jokim.sivillage.api.customer.application;

import com.jokim.sivillage.api.customer.entity.AuthUserDetail;
import com.jokim.sivillage.api.customer.infrastructure.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@ToString
@Service
@RequiredArgsConstructor
public class AuthCustomerDetailService implements UserDetailsService {

    private final CustomerRepository customerRepository;
    @Override
    public UserDetails loadUserByUsername(String uuid) throws UsernameNotFoundException {
        return new AuthUserDetail(customerRepository.findByUuid(uuid).orElseThrow(() -> new UsernameNotFoundException(uuid)));
    }

}