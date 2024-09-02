package com.jokim.sivillage.product.domain.option;

import jakarta.persistence.*;

@Entity
public class Etc {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 50)
    private String name;
    @Column(length = 50)
    private String value;
}
