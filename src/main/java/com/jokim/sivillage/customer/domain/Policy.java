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
public class Policy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Boolean essential1;
    private Boolean essential2;
    private Boolean essential3;
    private Boolean optional;

    @OneToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Builder
    public Policy(
            Long id,
            Boolean essential1,
            Boolean essential2,
            Boolean essential3,
            Boolean optional,
            Customer customer
    ){
        this.id = id;
        this.essential1 = essential1;
        this.essential2 = essential2;
        this.essential3 = essential3;
        this.optional = optional;
        this.customer = customer;
    }
}
