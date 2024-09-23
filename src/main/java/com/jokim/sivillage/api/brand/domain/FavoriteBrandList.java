package com.jokim.sivillage.api.brand.domain;

import jakarta.persistence.*;

@Entity
public class FavoriteBrandList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;

    private Long uuid;


}
