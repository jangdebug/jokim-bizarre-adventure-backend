package com.jokim.sivillage.api.hashtag.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Hashtag {

    @Id
    private Long id;

    @Column(length = 20, nullable = false)
    private String value;

}
