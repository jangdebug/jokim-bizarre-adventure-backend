package com.jokim.sivillage.api.customer.vo;

import lombok.Getter;
import lombok.ToString;

import java.util.Date;

//회원가입시에 받을 정보들
@ToString
@Getter
public class CustomerSignUpRequestVo {
    private String email;
    private String password;
    private String name;
    private Date birth;
    private String phone;

    private Boolean marketingSms;
    private Boolean marketingEmail;
    private Boolean marketingDm;
    private Boolean marketingCall;

    private Boolean policyEssential1;
    private Boolean policyEssential2;
    private Boolean policyEssential3;
    private Boolean policyEssential4;
    private Boolean policyOptional;

    private String zipCode;
    private String address;
    private String addressDetail;

}
