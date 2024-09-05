package com.jokim.sivillage.api.customer.application;

import com.jokim.sivillage.api.customer.dto.in.*;
import com.jokim.sivillage.api.customer.dto.out.OauthSignUpResponseDto;
import com.jokim.sivillage.api.customer.dto.out.SignInResponseDto;

public interface CustomerService {

    void signUp(SignUpDto signUpDto);

    SignInResponseDto signIn(SignInRequestDto signInRequestDto);

    OauthSignUpResponseDto oauthSignUp(OauthSignUpDto oauthSignUpDto);

    SignInResponseDto oauthpolicySignUp(OauthSignUpPolicyDto oauthSignUpPolicyDto);

    SignInResponseDto oauthSignIn(OauthSignInRequestDto oauthSignInRequestDto);

    SignInResponseDto refreshAccessToken(String refreshToken);

    void logout(String accessToken);


}
