package com.jokim.sivillage.api.customer.dto.out;

import com.jokim.sivillage.api.customer.vo.out.OauthSignUpResponseVo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OauthSignUpResponseDto {

    private String accessToken;
    private String uuid;

    public OauthSignUpResponseVo toResponseVo(){
        return OauthSignUpResponseVo.builder()
            .accessToken(accessToken)
            .uuid(uuid)
            .build();
    }

}
