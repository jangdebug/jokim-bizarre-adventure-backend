package com.jokim.sivillage.api.customer.dto.in;

import com.jokim.sivillage.api.customer.domain.Address;
import com.jokim.sivillage.api.customer.vo.in.CustomerCreateAddressRequestVo;
import lombok.*;

import java.util.UUID;

@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerCreateAddressRequestDto {
    private String accessToken;
    private String addressName;
    private String recipient;
    private String phone;
    private String zipCode;
    private String address;
    private String addressDetail;
    private String message;

    public static CustomerCreateAddressRequestDto toDto(String accessToken, CustomerCreateAddressRequestVo customerCreateAddressRequestVo){
        return CustomerCreateAddressRequestDto.builder()
                .accessToken(accessToken)
                .addressName(customerCreateAddressRequestVo.getAddressName())
                .recipient(customerCreateAddressRequestVo.getRecipient())
                .phone(customerCreateAddressRequestVo.getPhone())
                .zipCode(customerCreateAddressRequestVo.getZipCode())
                .address(customerCreateAddressRequestVo.getAddress())
                .addressDetail(customerCreateAddressRequestVo.getAddressDetail())
                .message(customerCreateAddressRequestVo.getMessage())
                .build();
    }


    public Address toEntity(String uuid){
        return Address.builder()
                .uuid(uuid)
                .addressCode(UUID.randomUUID().toString())
                .addressName(addressName)
                .recipient(recipient)
                .phone(phone)
                .zipCode(zipCode)
                .address(address)
                .addressDetail(addressDetail)
                .message(message)
                .build();
    }

}
