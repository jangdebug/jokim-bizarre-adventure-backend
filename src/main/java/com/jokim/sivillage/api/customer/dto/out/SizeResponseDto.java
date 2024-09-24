package com.jokim.sivillage.api.customer.dto.out;

import com.jokim.sivillage.api.customer.domain.Customer;
import com.jokim.sivillage.api.customer.domain.CustomerSize;
import com.jokim.sivillage.api.customer.vo.out.SizeResponsVo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SizeResponseDto {

    private Integer weight;
    private Integer height;
    private String topSize;
    private String bottomSize;
    private String footSize;
    private Boolean agreement;

    public static SizeResponseDto toDto(CustomerSize customerSize) {
        return SizeResponseDto.builder()
            .weight(customerSize.getWeight())
            .height(customerSize.getHeight())
            .topSize(customerSize.getTopSize())
            .bottomSize(customerSize.getBottomSize())
            .footSize(customerSize.getFootSize())
            .agreement(customerSize.getAgreement())
            .build();
    }

    public SizeResponsVo toVo() {
        return SizeResponsVo.builder()
            .weight(weight)
            .height(height)
            .topSize(topSize)
            .bottomSize(bottomSize)
            .footSize(footSize)
            .agreement(agreement)
            .build();
    }
}
