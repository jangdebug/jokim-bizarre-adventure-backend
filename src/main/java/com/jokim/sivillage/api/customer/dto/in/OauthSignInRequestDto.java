package com.jokim.sivillage.api.customer.dto.in;

import com.jokim.sivillage.api.customer.domain.SocialCustomer;
import com.jokim.sivillage.api.customer.vo.in.OauthSignInRequestVo;
import lombok.*;

@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OauthSignInRequestDto {

    private String oauthProviderId;
    private String email;
    private String name;

    public static OauthSignInRequestDto toDto(OauthSignInRequestVo oauthSignInRequestVo){
        return OauthSignInRequestDto.builder()
            .oauthProviderId(oauthSignInRequestVo.getOauthSocialId())
            .email(oauthSignInRequestVo.getEmail())
            .name(oauthSignInRequestVo.getName())
            .build();
    }

    public SocialCustomer toEntity(String uuid){
        return SocialCustomer.builder()
            .uuid(uuid)
            .name(name)
            .oauthProviderId(oauthProviderId)
            .build();
    }
}
