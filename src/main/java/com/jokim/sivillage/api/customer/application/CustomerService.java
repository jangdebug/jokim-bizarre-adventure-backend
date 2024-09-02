package com.jokim.sivillage.api.customer.application;

import com.jokim.sivillage.api.customer.dto.OauthCustomerSignUpDto;
import com.jokim.sivillage.api.customer.dto.CustomerSignUpDto;
import com.jokim.sivillage.api.customer.dto.SignInRequestDto;
import com.jokim.sivillage.api.customer.dto.SignInResponseDto;

public interface CustomerService {
    void signUp(CustomerSignUpDto customerSignUpDto);
    //void oauthSignUp(OauthCustomerSignUpDto oauthCustomerSignUpDto);
    SignInResponseDto signIn(SignInRequestDto signInRequestDto);
}
