package com.jokim.sivillage.api.bridge.productmedialist.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"productCode", "mediaCode"})})
@Entity
public class ProductMediaList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 36)
    private String productCode;

    @Column(nullable = false, length = 36)
    private String mediaCode;

    @Column(nullable = false)
    private Boolean isThumbnail;

}
