package com.jokim.sivillage.api.brand.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.ToString;

@Entity
@Getter
@ToString
public class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 36)
    private String brandCode;
    @Column(length = 30)
    private String mainName;
    @Column(length = 30)
    private String subName;

}
