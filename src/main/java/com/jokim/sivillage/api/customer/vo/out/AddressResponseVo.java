package com.jokim.sivillage.api.customer.vo.out;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddressResponseVo {
    private String addressCode;
    private String addressName;
    private String recipient;
    private String dispCellNo;
    private String phone;
    private String zipCode;
    private String addressRoad;
    private String addressJibeon;
    private String addressDetail;
    private Boolean isDefault;
    private Boolean deliveryPolicy;
}
