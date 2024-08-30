package com.jokim.sivillage.customer.vo;

import com.jokim.sivillage.customer.domain.State;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerResponseVo {
    private Long id;
    private String customerUuid;
    private String email;
    private String password;
    private String name;
    private Date birth;
    private String phone;
    private State state;
}
