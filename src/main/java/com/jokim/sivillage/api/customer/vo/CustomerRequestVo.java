package com.jokim.sivillage.api.customer.vo;

import lombok.Getter;

import java.util.Date;

@Getter
public class CustomerRequestVo {

    private Long id;
    private String uuid;
    private String email;
    private String password;
    private String name;
    private Date birth;
    private String phone;

}
