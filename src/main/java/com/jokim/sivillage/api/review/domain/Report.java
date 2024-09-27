package com.jokim.sivillage.api.review.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,length = 36)
    private String uuid;

    @Column(nullable = false, length = 36)
    private String reviewCode;

    @Column(columnDefinition = "boolean default false")
    private Boolean isRead;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(nullable = false, length = 50)
    private String reporterName;

    @Column(nullable = false, length = 50)
    private String email;

    @Column(columnDefinition = "boolean default false")
    private Boolean allowEmailReply;

    @Column(nullable = false, length = 20)
    private String phone;

    @Column(columnDefinition = "boolean default false")
    private Boolean allowSnsRelpy;
}
