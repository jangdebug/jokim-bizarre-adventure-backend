package com.jokim.sivillage.api.customer.dto.out;

import com.jokim.sivillage.api.customer.domain.Address;
import com.jokim.sivillage.api.customer.vo.out.AddressResponseVo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddressResponseDto {

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

    public static AddressResponseDto toDto(Address address, Boolean isDefault){
        return AddressResponseDto.builder()
            .addressCode(address.getAddressCode())
            .addressName(address.getAddressName())
            .recipient(address.getRecipient())
            .dispCellNo(address.getDispCellNo())
            .phone(address.getPhone())
            .zipCode(address.getZipCode())
            .addressRoad(address.getAddressRoad())
            .addressJibeon(address.getAddressJibeon())
            .addressDetail(address.getAddressDetail())
            .isDefault(isDefault)
            .deliveryPolicy(address.getDeliveryPolicy())
            .build();
    }

    public AddressResponseVo toVo(){
        return AddressResponseVo.builder()
            .addressCode(addressCode)
            .addressName(addressName)
            .recipient(recipient)
            .dispCellNo(dispCellNo)
            .phone(phone)
            .zipCode(zipCode)
            .addressRoad(addressRoad)
            .addressJibeon(addressJibeon)
            .addressDetail(addressDetail)
            .isDefault(isDefault)
            .deliveryPolicy(deliveryPolicy)
            .build();
    }
}
