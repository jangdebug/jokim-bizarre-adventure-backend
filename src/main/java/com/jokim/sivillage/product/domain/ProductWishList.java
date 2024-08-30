package com.jokim.sivillage.product.domain;


import jakarta.persistence.*;
@Entity
public class ProductWishList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(length = 36)
    private String uuid;


}
