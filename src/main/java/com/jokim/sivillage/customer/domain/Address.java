package com.jokim.sivillage.customer.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Entity
@ToString
@NoArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String addressName;
    private String recipient;
    private String phone;
    private String zipCode;
    private String address;
    private String addressDetail;
    private String message;

    @OneToOne(mappedBy = "address")
    private DefaultAddress defaultAddress;

    @Builder
    public Address(
            Long id,
            String addressName,
            String recipient,
            String phone,
            String zipCode,
            String address,
            String addressDetail,
            String message
    ){
        this.id = id;
        this.addressName = addressName;
        this.recipient = recipient;
        this.phone = phone;
        this.zipCode = zipCode;
        this.address = address;
        this.addressDetail = addressDetail;
        this.message = message;

    }
}
