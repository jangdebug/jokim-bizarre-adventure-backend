package com.jokim.sivillage.api.media.domain;

import jakarta.persistence.*;

@Entity
public class Media {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String url;

    // type 컬럼 ENUM 타입으로 추가 필요
}
