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
public class Marketing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Boolean marketingSms;
    private Boolean marketingEmail;
    private Boolean marketingDm;
    private Boolean marketingCall;

    @OneToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Builder
    public Marketing(
            Long id,
            Boolean marketingSms,
            Boolean marketingEmail,
            Boolean marketingDm,
            Boolean marketingCall,
            Customer customer
    ){
        this.id = id;
        this.marketingSms = marketingSms;
        this.marketingEmail = marketingEmail;
        this.marketingDm = marketingDm;
        this.marketingCall = marketingCall;
        this.customer = customer;
    }

}
