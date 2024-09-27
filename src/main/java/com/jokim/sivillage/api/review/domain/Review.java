package com.jokim.sivillage.api.review.domain;

import com.jokim.sivillage.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Review extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,length = 36)
    private String productCode;

    @Column(nullable = false,length = 36)
    private String uuid;

    @Column(nullable = false,length = 36)
    private String reviewCode;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private ReviewType reviewType;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Column(nullable = false)
    private Double starPoint;

    @Column(nullable = false)
    private Boolean state;

    @Column(nullable = false, length = 10)
    private String parsedEmail;

    @Column(nullable = false, length = 50)
    private String optionInfo;
}
