package com.jokim.sivillage.customer.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Entity
@Builder
@ToString
@NoArgsConstructor
public class DefaultAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Boolean isDefault;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToOne
    @JoinColumn(name = "address_id")
    private Address address;

    @Builder
    public DefaultAddress(
            Long id,
            Boolean isDefault,
            Customer customer,
            Address address
    ){
        this.id = id;
        this.isDefault = isDefault;
        this.customer = customer;
        this.address = address;
    }
}
