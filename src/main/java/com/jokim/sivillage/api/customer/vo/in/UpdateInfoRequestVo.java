package com.jokim.sivillage.api.customer.vo.in;

import lombok.Getter;

@Getter
public class UpdateInfoRequestVo {
    private String phone;
    private String address;
    private Boolean smsAgreement;
    private Boolean emailAgreement;
    private Boolean dmAgreement;
    private Boolean callAgreement;
    private Boolean tomboyInfoUsageRight;
}
