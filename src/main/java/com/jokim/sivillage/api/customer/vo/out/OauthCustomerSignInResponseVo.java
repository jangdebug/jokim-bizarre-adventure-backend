package com.jokim.sivillage.api.customer.vo.out;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class OauthCustomerSignInResponseVo {

    private String accessToken;
    private String refreshToken;
}
