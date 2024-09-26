package com.jokim.sivillage.api.customer.domain;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Entity
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerSize {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 36)
    private String uuid;

    @Column(nullable = false)
    private Integer weight;

    @Column(nullable = false)
    private Integer height;

    @Column(nullable = false, length = 10)
    private String topSize;

    @Column(nullable = false, length = 10)
    private String bottomSize;

    @Column(nullable = false, length = 10)
    private String footSize;

    @Column(nullable = false, columnDefinition = "boolean default false")
    private Boolean agreement;

}
