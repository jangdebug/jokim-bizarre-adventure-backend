package com.jokim.sivillage.api.event.domain;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 36, nullable = false)
    private String eventCode;

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

    @Column(length = 65535, nullable = false)
    private String detail;


    // ENUM 생성 및 default value 지정 필요
    @Column(length = 10, nullable = false)
    private String type;
    //////////////////////////////////////////////

}
