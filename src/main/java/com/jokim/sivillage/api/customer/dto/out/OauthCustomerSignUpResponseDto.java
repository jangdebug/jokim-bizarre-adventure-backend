package com.jokim.sivillage.api.customer.dto.out;

import com.jokim.sivillage.api.customer.vo.out.OauthCustomerSignUpResponseVo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OauthCustomerSignUpResponseDto {

    private String accessToken;
    private String uuid;

    public OauthCustomerSignUpResponseVo toResponseVo(){
        return OauthCustomerSignUpResponseVo.builder()
            .accessToken(accessToken)
            .uuid(uuid)
            .build();
    }

}
