package com.jokim.sivillage.api.customer.domain;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Entity
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Marketing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 36)
    private String uuid;

    @Column(nullable = false)
    private Boolean smsAgreement;

    @Column(nullable = false)
    private Boolean emailAgreement;

    @Column(nullable = false)
    private Boolean dmAgreement;

    @Column(nullable = false)
    private Boolean callAgreement;


}
