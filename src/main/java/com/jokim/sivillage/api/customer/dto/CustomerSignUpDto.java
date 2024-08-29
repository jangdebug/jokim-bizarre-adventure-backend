package com.jokim.sivillage.api.customer.dto;

import com.jokim.sivillage.api.customer.domain.Customer;
import com.jokim.sivillage.api.customer.vo.CustomerResponseVo;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerSignUpDto {

    private Long id;
    private String uuid;
    private String email;
    private String password;
    private String name;
    private Date birth;
    private String phone;

    public Customer toEntity() {
        return Customer.builder()
                .id(id)
                .uuid(uuid)
                .email(email)
                .password(password)
                .name(name)
                .birth(birth)
                .phone(phone)
                .build();
    }

    public CustomerResponseVo toResponseVo() {
        return CustomerResponseVo.builder()
                .id(id)
                .uuid(uuid)
                .email(email)
                .password(password)
                .name(name)
                .birth(birth)
                .phone(phone)
                .build();
    }
}
