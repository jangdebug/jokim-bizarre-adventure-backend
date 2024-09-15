package com.jokim.sivillage.api.customer.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RefreshTokenResponseVo {
    private String accessToken;
}
