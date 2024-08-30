package com.jokim.sivillage.hashtag.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Hashtag {

    @Id
    private Long hashtagId;
    @Column(length = 20)
    private String value;

}
