package com.jokim.sivillage.product.domain;

import com.jokim.sivillage.product.domain.option.Color;
import com.jokim.sivillage.product.domain.option.Etc;
import com.jokim.sivillage.product.domain.option.Size;
import jakarta.persistence.*;

import java.util.List;

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
