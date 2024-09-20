package com.jokim.sivillage.api.customer.dto.in;

import com.jokim.sivillage.api.customer.domain.Customer;
import com.jokim.sivillage.api.customer.vo.in.UpdatePasswordRequestVo;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UpdatePasswordRequestDto {
    private String accessToken;
    private String password;

    public static UpdatePasswordRequestDto toDto(String accessToken, UpdatePasswordRequestVo updatePasswordRequestVo) {
        return UpdatePasswordRequestDto.builder()
            .accessToken(accessToken)
            .password(updatePasswordRequestVo.getPassword())
            .build();
    }

    public Customer updateEntity(Customer customer, PasswordEncoder passwordEncoder) {
        return Customer.builder()
            .id(customer.getId())
            .uuid(customer.getUuid())                          // 기존 UUID 유지
            .email(customer.getEmail())                        // 기존 이메일 유지
            .password(passwordEncoder.encode(password))        // 비밀번호 업데이트
            .name(customer.getName())                          // 기존 이름 유지
            .birth(customer.getBirth())                        // 기존 생일 유지
            .phone(customer.getPhone())                        // 기존 전화번호 유지
            .address(customer.getAddress())                    // 기존 주소 유지
            .state(customer.getState())                        // 기존 상태 유지
            .build();                                          // 새 Customer 객체 반환
    }
}