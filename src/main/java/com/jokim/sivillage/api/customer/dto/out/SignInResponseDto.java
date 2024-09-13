package com.jokim.sivillage.api.customer.dto.out;

import com.jokim.sivillage.api.customer.vo.out.SignInResponseVo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignInResponseDto {

    private String accessToken;
    private String refreshToken;


    public static SignInResponseDto toDto(String accessToken, String refreshToken){
        return SignInResponseDto.builder()
            .accessToken(accessToken)
            .refreshToken(refreshToken)
            .build();
    }

    public SignInResponseVo toVo(){
        return SignInResponseVo.builder()
            .accessToken(accessToken)
            .refreshToken(refreshToken)
            .build();
    }

}