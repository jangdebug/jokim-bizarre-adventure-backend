package com.jokim.sivillage.api.customer.vo.in;

import jakarta.persistence.Column;
import lombok.Getter;

@Getter
public class CustomerCreateAddressRequestVo {

    private String addressName;
    private String recipient;
    private String phone;
    private String zipCode;
    private String address;
    private String addressDetail;
    private String message;
}
