package com.jokim.sivillage.api.customer.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Entity(name = "policy")
@Builder
@ToString
@NoArgsConstructor
public class Policy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Boolean essential1;
    @Column(nullable = false)
    private Boolean essential2;
    @Column(nullable = false)
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
