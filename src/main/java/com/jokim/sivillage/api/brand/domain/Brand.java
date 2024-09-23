package com.jokim.sivillage.api.brand.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 36)
    private String brandCode;

    @Column(length = 30)
    private String englishName;

    @Column(length = 30)
    private String koreanName;

    @Column(nullable = false, length = 10)
    private String englishInitial;

    @Column(nullable = false, length = 10)
    private String koreanInitial;

}
