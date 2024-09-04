package com.jokim.sivillage.api.customer.application;

import com.jokim.sivillage.api.customer.dto.in.*;
import com.jokim.sivillage.api.customer.dto.out.OauthCustomerSignUpResponseDto;
import com.jokim.sivillage.api.customer.dto.out.SignInResponseDto;

public interface CustomerService {

    void signUp(CustomerSignUpDto customerSignUpDto);

    SignInResponseDto signIn(SignInRequestDto signInRequestDto);

    OauthCustomerSignUpResponseDto oauthSignUp(OauthCustomerSignUpDto oauthCustomerSignUpDto);

    SignInResponseDto oauthpolicySignUp(OauthCustomerSignUpPolicyDto oauthCustomerSignUpPolicyDto);

    SignInResponseDto oauthSignIn(OauthCustomerSignInRequestDto oauthCustomerSignInRequestDto);

    void logout(String accessToken);


}
