package com.jokim.sivillage.api.customer.domain;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Entity
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Policy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 36)
    private String uuid;

    @Column(nullable = false ,columnDefinition = "boolean default false")
    private Boolean webUsageRight;

    @Column(nullable = false ,columnDefinition = "boolean default false")
    private Boolean integratedMemberRight;

    @Column(nullable = false ,columnDefinition = "boolean default false")
    private Boolean infoUsageRight;

    @Column(nullable = false ,columnDefinition = "boolean default false")
    private Boolean tomboyInfoUsageRight;

}
