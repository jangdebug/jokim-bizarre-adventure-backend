package com.jokim.sivillage.api.basket.domain;

import com.jokim.sivillage.common.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Basket extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 36)
    private String uuid;

    @Column(nullable = false, length = 36)
    private String productCode;

    @Column(nullable = false, length = 36)
    private String productOptionCode;

    @Column(nullable = false, length = 36)
    private String basketCode;

    @Column(nullable = false)
    private Short quantity;

    @Column(nullable = false, columnDefinition = "boolean default true")
    private Boolean isChecked;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private BasketState basketState;

}
