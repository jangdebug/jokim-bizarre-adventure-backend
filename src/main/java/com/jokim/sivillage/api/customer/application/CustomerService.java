package com.jokim.sivillage.api.customer.application;

import com.jokim.sivillage.api.customer.domain.Customer;
import com.jokim.sivillage.api.customer.dto.DuplicateEmailDto;
import com.jokim.sivillage.api.customer.dto.RefreshTokenRequestDto;
import com.jokim.sivillage.api.customer.dto.RefreshTokenResponseDto;
import com.jokim.sivillage.api.customer.dto.in.OauthSignInRequestDto;
import com.jokim.sivillage.api.customer.dto.in.SignInRequestDto;
import com.jokim.sivillage.api.customer.dto.in.SignUpRequestDto;
import com.jokim.sivillage.api.customer.dto.in.UpdateRequestDto;
import com.jokim.sivillage.api.customer.dto.out.SignInResponseDto;

import java.util.Optional;

public interface CustomerService {

    void signUp(SignUpRequestDto signUpRequestDto);

    void update(UpdateRequestDto updateRequestDto);

    SignInResponseDto signIn(SignInRequestDto signInRequestDto);

    SignInResponseDto oauthSignIn(OauthSignInRequestDto oauthSignInRequestDto);

    RefreshTokenResponseDto refreshAccessToken(RefreshTokenRequestDto refreshTokenRequestDto);


    Optional<Customer> findUserByEmail(String email);

    void duplicateEmail(DuplicateEmailDto duplicateEmailDto);
}
