package com.jokim.sivillage.api.product.domain.option;

import jakarta.persistence.*;

@Entity
public class Color {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20)
    private String value;
}
