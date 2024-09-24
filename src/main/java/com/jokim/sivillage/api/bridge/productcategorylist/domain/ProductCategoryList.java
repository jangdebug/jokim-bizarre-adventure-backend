package com.jokim.sivillage.api.bridge.productcategorylist.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class ProductCategoryList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 36)
    private String productCode;

    @Column(nullable = false, length = 36)
    private String mainCategoryCode;

    @Column(length = 36)
    private String secondaryCategoryCode;

    @Column(length = 36)
    private String tertiaryCategoryCode;

    @Column(length = 36)
    private String quaternaryCategoryCode;

    @Column(nullable = false, columnDefinition = "boolean default true")
    private Boolean isOnSale;

    @Column(nullable = false, columnDefinition = "boolean default true")
    private Boolean isOnSale;

}
