package com.jokim.sivillage.api.review.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductEvaluationManage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long evaluationItemNameId;

    @Column(nullable = false, length = 36)
    private String productCode;
}
