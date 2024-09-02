package com.jokim.sivillage.api.customer.vo;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class SignInRequestVo {
    private String email;
    private String password;
}
