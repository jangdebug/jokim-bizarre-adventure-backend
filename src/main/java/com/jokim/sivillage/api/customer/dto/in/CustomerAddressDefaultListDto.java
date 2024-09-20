package com.jokim.sivillage.api.customer.dto.in;

import com.jokim.sivillage.api.customer.domain.CustomerAddressDefaultList;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CustomerAddressDefaultListDto {
    private Long id;
    private String accessToken;
    private String uuid;
    private String addressCode;
    private Boolean isDefault;


    public static CustomerAddressDefaultList toFirstEntity(String uuid, String addressCode){
        return CustomerAddressDefaultList.builder()
            .uuid(uuid)
            .addressCode(addressCode)
            .isDefault(true)
            .build();
    }

    public static CustomerAddressDefaultList toEntity(String uuid, String addressCode){
        return CustomerAddressDefaultList.builder()
            .uuid(uuid)
            .addressCode(addressCode)
            .isDefault(false)
            .build();
    }

    public static CustomerAddressDefaultListDto toSetDefaultAddress(String addressCode, String accessToken){
        return CustomerAddressDefaultListDto.builder()
            .accessToken(accessToken)
            .addressCode(addressCode)
            .build();
    }

    public CustomerAddressDefaultList toOldDefaultAddressListEntity(CustomerAddressDefaultList customerAddressDefaultList){
        return CustomerAddressDefaultList.builder()
            .id(customerAddressDefaultList.getId())
            .uuid(customerAddressDefaultList.getUuid())
            .addressCode(customerAddressDefaultList.getAddressCode())
            .isDefault(false)
            .build();
    }
    public CustomerAddressDefaultList toNewDefaultAddressListEntity(CustomerAddressDefaultList customerAddressDefaultList){
        return CustomerAddressDefaultList.builder()
            .id(customerAddressDefaultList.getId())
            .uuid(customerAddressDefaultList.getUuid())
            .addressCode(customerAddressDefaultList.getAddressCode())
            .isDefault(true)
            .build();
    }

}
