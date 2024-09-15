package com.jokim.sivillage.api.customer.dto;

import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class RefreshTokenRequestDto {
    private String refreshToken;

    public static RefreshTokenRequestDto toDto(String refreshToken){
        return RefreshTokenRequestDto.builder()
            .refreshToken(refreshToken)
            .build();
    }
}
