package com.jokim.sivillage.api.event.domain;

import jakarta.persistence.*;

@Entity
public class EventProductList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


}
