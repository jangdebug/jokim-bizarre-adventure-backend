package com.jokim.sivillage.api.customer.vo.in;

import lombok.Getter;

@Getter
public class OauthCustomerSignInRequestVo {

    private String email;
    private String provider;
}
