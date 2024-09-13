package com.jokim.sivillage.api.customer.dto;

import com.jokim.sivillage.api.customer.vo.RefreshTokenResponseVo;
import lombok.*;

// RefreshTokenResponseDto.java
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class RefreshTokenResponseDto {
    private String accessToken;

    public static RefreshTokenResponseDto toDto(String accessToken){
        return RefreshTokenResponseDto.builder()
            .accessToken(accessToken)
            .build();
    }

    public RefreshTokenResponseVo toVo(){
        return RefreshTokenResponseVo.builder()
            .accessToken(accessToken)
            .build();
    }
}