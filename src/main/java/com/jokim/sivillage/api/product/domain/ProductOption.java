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
    @JoinColumn(name = "product_id")
    private Product product;

//    @OneToMany(mappedBy = "size")
//    private List<Size> size;
//
//    @OneToMany(mappedBy = "color")
//    private List<Color> color;
//
//    @OneToMany(mappedBy = "etc")
//    private List<Etc> etc;

    @ManyToOne
    @JoinColumn(name = "size_id")
    private Size size;

    @ManyToOne
    @JoinColumn(name = "color_id")
    private Color color;


    @ManyToOne
    @JoinColumn(name = "etc_id")
    private Etc etc;

}
