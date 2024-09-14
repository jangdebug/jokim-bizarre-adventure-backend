package com.jokim.sivillage.api.customer.vo.in;

import lombok.Getter;

@Getter
public class OauthSignInRequestVo {

    private String oauthSocialId;
    private String email;
    private String name;
}
