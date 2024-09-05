package com.jokim.sivillage.api.customer.vo.in;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class OauthSignUpRequestPolicyVo {

    private String accessToken;
    private String uuid;
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
