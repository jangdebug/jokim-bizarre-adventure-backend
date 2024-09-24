package com.jokim.sivillage.api.customer.vo.in;

import lombok.Getter;

@Getter
public class CustomerAddressRequestVo {

    private String addressName;
    private String recipient;
    private String dispCellNo;
    private String phone;
    private String zipCode;
    private String addressRoad;
    private String addressJibeon;
    private Boolean isDefault;
    private String addressDetail;
    private Boolean deliveryPolicy;
}
