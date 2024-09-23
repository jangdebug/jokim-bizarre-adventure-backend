package com.jokim.sivillage.api.review.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EvaluationItemValue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private EvaluationItemName evaluationItemName;

    @Column(nullable = false, length = 36)
    private String reviewCode;

    @Column(nullable = false, length = 30)
    private String value;

    @Column(columnDefinition = "boolean default false")
    private Boolean isBest;

    @Column(columnDefinition = "BIGINT")
    private Long count;

}
