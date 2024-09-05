package com.jokim.sivillage.api.customer.dto.in;

import com.jokim.sivillage.api.customer.domain.*;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class OauthSignUpPolicyDto {

    private String accessToken;
    private String uuid;
    private Long marketingId;
    private Boolean marketingSms;
    private Boolean marketingEmail;
    private Boolean marketingDm;
    private Boolean marketingCall;

    private Long policyId;
    private Boolean essential1;
    private Boolean essential2;
    private Boolean essential3;
    private Boolean optional;

    private Long addressId;
    private String zipCode;
    private String address;
    private String addressDetail;
    private String message;

    private Long defaultAddressId;

    private Customer customer;


    public Marketing toMarketingEntity(Customer customer) {
        return Marketing.builder()
            .id(marketingId)
            .marketingSms(marketingSms)
            .marketingEmail(marketingEmail)
            .marketingDm(marketingDm)
            .marketingCall(marketingCall)
            .customer(customer)
            .build();
    }

    public Policy toPolicyEntity(Customer customer) {
        return Policy.builder()
            .id(policyId)
            .essential1(essential1)
            .essential2(essential2)
            .essential3(essential3)
            .optional(optional)
            .customer(customer)
            .build();
    }

    public Address toAdressEntity(String name, String phone) {
        return Address.builder()
            .id(addressId)
            .addressName("기본 배송지")
            .recipient(name)
            .phone(phone)
            .zipCode(zipCode)
            .address(address)
            .addressDetail(addressDetail)
            .message(message)
            .build();
    }

    public DefaultAddress toDefaultAddressEntity(Customer customer, Address address) {
        return DefaultAddress.builder()
            .id(defaultAddressId)
            .isDefault(true)
            .address(address)
            .customer(customer)
            .build();

    }

}
