package com.jokim.sivillage.api.customer.vo.out;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class OauthCustomerSignUpResponseVo {

    private String accessToken;
    private String uuid;

}
