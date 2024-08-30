package com.jokim.sivillage.customer.application;

import com.jokim.sivillage.customer.dto.CustomerSignUpDto;
import com.jokim.sivillage.customer.infrastructure.CustomerRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public interface CustomerService {
    void signUp(CustomerSignUpDto customerSignUpDto);
}
