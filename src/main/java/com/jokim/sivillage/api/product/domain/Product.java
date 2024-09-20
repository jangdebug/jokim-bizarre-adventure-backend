package com.jokim.sivillage.api.product.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Entity
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
