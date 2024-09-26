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


    public static CustomerAddressDefaultList toEntity(String uuid, Boolean isDefault, String addressCode){
        return CustomerAddressDefaultList.builder()
            .uuid(uuid)
            .addressCode(addressCode)
            .isDefault(isDefault)
            .build();
    }

    public static CustomerAddressDefaultListDto toSetDefaultAddress(String addressCode, String accessToken){
        return CustomerAddressDefaultListDto.builder()
            .accessToken(accessToken)
            .addressCode(addressCode)
            .build();
    }

    public static CustomerAddressDefaultListDto toDto(String uuid,String accessToken, String addressCode, Boolean isDefault){
        return CustomerAddressDefaultListDto.builder()
            .uuid(uuid)
            .addressCode(addressCode)
            .accessToken(accessToken)
            .isDefault(isDefault)
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
