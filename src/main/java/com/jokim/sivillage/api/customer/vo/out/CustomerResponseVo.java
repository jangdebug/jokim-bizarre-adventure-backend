package com.jokim.sivillage.api.customer.vo.out;

import com.jokim.sivillage.api.customer.domain.State;
import lombok.*;

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
