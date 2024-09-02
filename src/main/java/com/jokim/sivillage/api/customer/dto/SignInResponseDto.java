package com.jokim.sivillage.api.customer.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignInResponseDto {

    private String accessToken;
    private String refreshToken;
    private String name;
    private String uuid;

//    todo : to vo SignInResponseVo





}