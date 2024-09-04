package com.jokim.sivillage.api.customer.vo.in;

import lombok.Getter;
import lombok.ToString;

import java.util.Date;

@ToString
@Getter
public class OauthCustomerSignUpRequestVo {

    private String accessToken;
    private String provider; // OAuth 공급자 (예: "google", "facebook")
    private String email;
    private String name;
    private Date birth;
    private String phone;
}
