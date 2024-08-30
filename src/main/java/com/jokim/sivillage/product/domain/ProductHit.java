package com.jokim.sivillage.product.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class ProductHit {
    @Id
    @Column(name = "product_hit_id")
    private Long productHitId;
    private Long productId;
    private Long hit;


}
