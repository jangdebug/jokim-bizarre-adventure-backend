package com.jokim.sivillage.api.customer.dto.in;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OauthSignInRequestDto {

    private String email;
    private String provider;
}
