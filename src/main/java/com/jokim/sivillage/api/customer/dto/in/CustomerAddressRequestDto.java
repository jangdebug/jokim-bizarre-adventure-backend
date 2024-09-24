package com.jokim.sivillage.api.customer.dto.in;

import com.jokim.sivillage.api.customer.domain.Address;
import com.jokim.sivillage.api.customer.vo.in.CustomerAddressRequestVo;
import com.jokim.sivillage.api.customer.vo.in.CustomerAddressUpdateVo;
import lombok.*;

import java.util.UUID;

@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerAddressRequestDto {
    private Long id;
    private String addressCode;
    private String accessToken;
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

    public static CustomerAddressRequestDto toDto(String accessToken, CustomerAddressRequestVo customerAddressRequestVo){
        return CustomerAddressRequestDto.builder()
            .accessToken(accessToken)
            .addressName(customerAddressRequestVo.getAddressName())
            .recipient(customerAddressRequestVo.getRecipient())
            .dispCellNo(customerAddressRequestVo.getDispCellNo())
            .phone(customerAddressRequestVo.getPhone())
            .zipCode(customerAddressRequestVo.getZipCode())
            .addressRoad(customerAddressRequestVo.getAddressRoad())
            .addressJibeon(customerAddressRequestVo.getAddressJibeon())
            .isDefault(customerAddressRequestVo.getIsDefault()) //디폴트 추가
            .addressDetail(customerAddressRequestVo.getAddressDetail())
            .deliveryPolicy(customerAddressRequestVo.getDeliveryPolicy())
            .build();
    }


    public Address toEntity(String uuid){
        return Address.builder()
            .uuid(uuid)
            .addressCode(UUID.randomUUID().toString())
            .addressName(addressName)
            .recipient(recipient)
            .dispCellNo(dispCellNo)
            .phone(phone)
            .zipCode(zipCode)
            .addressRoad(addressRoad)
            .addressJibeon(addressJibeon)
            .addressDetail(addressDetail)
            .deliveryPolicy(deliveryPolicy)
            .build();
    }

    public static CustomerAddressRequestDto toUpdateDto(String accessToken,
        CustomerAddressUpdateVo customerAddressUpdateVo){
        return CustomerAddressRequestDto.builder()
            .accessToken(accessToken)
            .addressCode(customerAddressUpdateVo.getAddressCode())
            .addressName(customerAddressUpdateVo.getAddressName())
            .recipient(customerAddressUpdateVo.getRecipient())
            .dispCellNo(customerAddressUpdateVo.getDispCellNo())
            .phone(customerAddressUpdateVo.getPhone())
            .zipCode(customerAddressUpdateVo.getZipCode())
            .addressRoad(customerAddressUpdateVo.getAddressRoad())
            .addressJibeon(customerAddressUpdateVo.getAddressJibeon())
            .isDefault(customerAddressUpdateVo.getIsDefault())
            .addressDetail(customerAddressUpdateVo.getAddressDetail())
            .deliveryPolicy(customerAddressUpdateVo.getDeliveryPolicy())
            .build();
    }

    public Address updateEntity(Address address){
        return Address.builder()
            .id(address.getId())
            .uuid(address.getUuid())
            .addressCode(address.getAddressCode())
            .addressName(addressName)
            .recipient(recipient)
            .dispCellNo(dispCellNo)
            .phone(phone)
            .zipCode(zipCode)
            .addressRoad(addressRoad)
            .addressJibeon(addressJibeon)
            .addressDetail(addressDetail)
            .deliveryPolicy(deliveryPolicy)
            .build();
    }

}
