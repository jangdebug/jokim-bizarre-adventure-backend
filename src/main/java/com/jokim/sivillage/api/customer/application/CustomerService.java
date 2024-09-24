package com.jokim.sivillage.api.customer.application;

import com.jokim.sivillage.api.customer.domain.Customer;
import com.jokim.sivillage.api.customer.domain.CustomerAddressDefaultList;
import com.jokim.sivillage.api.customer.dto.DuplicateEmailDto;
import com.jokim.sivillage.api.customer.dto.RefreshTokenRequestDto;
import com.jokim.sivillage.api.customer.dto.RefreshTokenResponseDto;
import com.jokim.sivillage.api.customer.dto.in.*;
import com.jokim.sivillage.api.customer.dto.out.AddressResponseDto;
import com.jokim.sivillage.api.customer.dto.out.SignInResponseDto;
import com.jokim.sivillage.api.customer.dto.in.CustomerSizeRequestDto;
import com.jokim.sivillage.api.customer.dto.in.UpdateInfoRequestDto;
import com.jokim.sivillage.api.customer.dto.in.UpdatePasswordRequestDto;

import com.jokim.sivillage.api.customer.dto.out.SizeResponseDto;
import java.util.List;
import java.util.Optional;

public interface CustomerService {

    void signUp(SignUpRequestDto signUpRequestDto);

    void updatePassword(UpdatePasswordRequestDto updatePasswordRequestDto);

    void updateInfo(UpdateInfoRequestDto updateInfoRequestDto);

    void createAddress(CustomerAddressRequestDto customerAddressRequestDto);

    void updateAddress(CustomerAddressRequestDto customerAddressRequestDto);

    void setDefaultAddress(CustomerAddressDefaultListDto customerAddressDefaultListDto);

    void deleteAddress(String addressCode);

    //void createCustomerSize(CustomerSizeRequestDto customerSizeRequestDto);

    //void updateCustomerSize(CustomerSizeRequestDto customerSizeRequestDto);

    SizeResponseDto getCustomerSize(String accessToken);

    AddressResponseDto getAddressDetail(String addressCode);

    void saveOrUpdateCustomerSize(CustomerSizeRequestDto customerSizeRequestDto);

    SignInResponseDto signIn(SignInRequestDto signInRequestDto);

    SignInResponseDto oauthSignIn(OauthSignInRequestDto oauthSignInRequestDto);

    RefreshTokenResponseDto refreshAccessToken(RefreshTokenRequestDto refreshTokenRequestDto);

    List<AddressResponseDto> getAddress(String accessToken);


    Optional<Customer> findUserByEmail(String email);

    void duplicateEmail(DuplicateEmailDto duplicateEmailDto);
}
