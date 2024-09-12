package com.jokim.sivillage.api.customer.dto.in;

import com.jokim.sivillage.api.customer.domain.*;
import lombok.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class OauthSignUpDto {

    private Long id;
    private String accessToken;
    private String email;
    private String provider; // OAuth 공급자 (예: "google", "facebook")
    private String name;
    private Date birth;
    private String phone;
    private State state;


    private Customer customer;

    public Customer toEntity(String customerUuid, State state) {
        return Customer.builder()
            .id(id)
            .email(email)
            .provider(provider)
            .customerUuid(customerUuid)
            .name(name)
            .birth(birth)
            .phone(phone)
            .state(state)
            .build();
    }

}
