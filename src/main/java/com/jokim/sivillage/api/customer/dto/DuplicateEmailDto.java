package com.jokim.sivillage.api.customer.dto;

import com.jokim.sivillage.api.customer.vo.DuplicateEmailVo;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class DuplicateEmailDto {
    private String email;

    public static DuplicateEmailDto toDto(DuplicateEmailVo duplicateEmailVo){
        return DuplicateEmailDto.builder()
            .email(duplicateEmailVo.getEmail())
            .build();
    }
}
