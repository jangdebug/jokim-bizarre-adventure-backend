package com.jokim.sivillage.event.domain;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 50, nullable = false)
    private String title;
    @Column(length = 50)
    private String subTitle;
    @Column(length = 255)
    private String description;
    @Column(nullable = false)
    private Date startDate;
    @Column(nullable = false)
    private Date expireDate;
    @Column(nullable = false)
    private String detail;
    @Column(length = 10)
    private String type;


}
