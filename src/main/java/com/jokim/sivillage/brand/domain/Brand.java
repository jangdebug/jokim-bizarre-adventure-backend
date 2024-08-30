package com.jokim.sivillage.brand.domain;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long brandId;
    @Column(length = 30)
    private String mainName;
    @Column(length = 30)
    private String subName;
    private String badgeImage;


}
