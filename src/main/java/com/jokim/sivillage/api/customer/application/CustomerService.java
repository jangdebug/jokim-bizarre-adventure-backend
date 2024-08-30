package com.jokim.sivillage.api.customer.application;

import com.jokim.sivillage.api.customer.dto.CustomerSignUpDto;

public interface CustomerService {
    void signUp(CustomerSignUpDto customerSignUpDto);
}
