package com.jokim.sivillage.api.customer.dto.in;

import com.jokim.sivillage.api.customer.vo.in.SignInRequestVo;
import lombok.*;

@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignInRequestDto {

    private String email;
    private String password;

    public static SignInRequestDto toDto(SignInRequestVo signInRequestVo){
        return SignInRequestDto.builder()
            .email(signInRequestVo.getEmail())
            .password(signInRequestVo.getPassword())
            .build();
    }

}