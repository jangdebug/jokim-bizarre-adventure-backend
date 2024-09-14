package com.jokim.sivillage.api.customer.vo.in;

import lombok.Getter;
import lombok.ToString;

import java.util.Date;

//회원가입시에 받을 정보들
@ToString
@Getter
public class SignUpRequestVo {

    private String email;
    private String password;
    private String name;
    private Date birth;
    private String phone;
    private String address;

    private Boolean smsAgreement;
    private Boolean emailAgreement;
    private Boolean dmAgreement;
    private Boolean callAgreement;

    private Boolean webUsageRight;
    private Boolean integratedMemberRight;
    private Boolean infoUsageRight;
    private Boolean tomboyInfoUsageRight;

}
