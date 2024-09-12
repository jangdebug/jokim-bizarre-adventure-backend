package com.jokim.sivillage.api.event.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.ToString;

@Entity
@Getter
@ToString
public class EventWishList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 36)
    private String eventCode;

    @Column(nullable = false, length = 36)
    private String uuid;

    @Column(nullable = false)
    private Boolean isChecked;

}
