package com.jokim.sivillage.api.customer.domain;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Entity
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 36)
    private String uuid;

    @Column(nullable = false,length = 36)
    private String addressCode;

    @Column(length = 50)
    private String addressName;

    @Column(length = 50)
    private String recipient;

    @Column(length = 10)
    private String dispCellNo;

    @Column(length = 20)
    private String phone;

    @Column(nullable = false,length = 20)
    private String zipCode;

    @Column(nullable = false,length = 255)
    private String addressRoad;

    @Column(nullable = false,length = 255)
    private String addressJibeon;

    @Column(length = 255)
    private String addressDetail;

    @Column(nullable = false)
    private Boolean deliveryPolicy;
}
