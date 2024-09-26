package com.jokim.sivillage.api.product.domain;

import com.jokim.sivillage.api.product.domain.option.Color;
import com.jokim.sivillage.api.product.domain.option.Size;
import com.jokim.sivillage.api.product.domain.option.Etc;
import jakarta.persistence.*;

@Entity
public class ProductOption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "size_id")
    private Size size;

    @ManyToOne
    @JoinColumn(name = "color_id")
    private Color color;

    @ManyToOne
    @JoinColumn(name = "etc_id")
    private Etc etc;

    @Column(nullable = false, length = 36)
    private String productCode;

    @Column(nullable = false, length = 36)
    private String productOptionCode;

    @Column(nullable = false)
    private Integer stock;


}
