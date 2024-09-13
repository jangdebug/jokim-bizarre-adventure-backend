package com.jokim.sivillage.api.customer.dto.in;

import com.jokim.sivillage.api.customer.vo.in.LogoutRequestVo;
import lombok.*;

@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LogoutRequestDto {

    private String accessToken;

    public static LogoutRequestDto toDto(LogoutRequestVo logoutRequestVo){
        return LogoutRequestDto.builder()
            .accessToken(logoutRequestVo.getAccessToken())
            .build();
    }
}
