package com.jokim.sivillage.api.review.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "product_statistic", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"productCode", "evaluationItemNameId"})
})
public class ProductStatistic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 36)
    private String productCode;

    @Column(nullable = false)
    private Long evaluationItemNameId;

    @Column(nullable = false)
    private Long evaluationItemValueId;

    @Column(nullable = false)
    private Integer evaluationItemNameRate;

}
