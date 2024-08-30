package com.jokim.sivillage.product.domain;

import com.jokim.sivillage.hashtag.domain.Hashtag;
import jakarta.persistence.*;

@Entity
public class ProductHashtag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productHashtagId;

    @ManyToOne
    private Product product;

    @OneToOne
    private Hashtag hashtag;


}
