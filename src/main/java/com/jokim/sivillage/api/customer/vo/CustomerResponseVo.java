package com.jokim.sivillage.api.customer.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerResponseVo {

    private Long id;
    private String uuid;
    private String email;
    private String password;
    private String name;
    private Date birth;
    private String phone;

}
