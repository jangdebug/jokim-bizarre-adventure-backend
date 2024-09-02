package com.jokim.sivillage.product.domain;


import com.jokim.sivillage.brand.domain.Brand;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Entity
@Getter
@ToString
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;
    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @Column(length = 255, nullable = false)
    private String productName;
    @Column(nullable = true)
    private boolean isOnSale;
    @Column(nullable = false)
    private String detail;
    @Column(nullable = false)
    private Double price;
    @OneToMany(mappedBy = "product")
    private List<ProductOption> options;





}
