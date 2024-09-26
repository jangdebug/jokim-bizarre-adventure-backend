package com.jokim.sivillage.api.customer.dto.in;

import com.jokim.sivillage.api.customer.domain.CustomerSize;
import com.jokim.sivillage.api.customer.vo.in.CustomerSizeRequestVo;
import lombok.*;

@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerSizeRequestDto {

    private Long id;
    private String uuid;
    private String accessToken;
    private Integer weight;
    private Integer height;
    private String topSize;
    private String bottomSize;
    private String footSize;
    private Boolean agreement;

    public static CustomerSizeRequestDto toDto(String accessToken,
        CustomerSizeRequestVo customerSizeRequestVo) {
        return CustomerSizeRequestDto.builder()
            .accessToken(accessToken)
            .weight(customerSizeRequestVo.getWeight())
            .height(customerSizeRequestVo.getHeight())
            .topSize(customerSizeRequestVo.getTopSize())
            .bottomSize(customerSizeRequestVo.getBottomSize())
            .footSize(customerSizeRequestVo.getFootSize())
            .agreement(customerSizeRequestVo.getAgreement())
            .build();
    }

    public CustomerSize toEntity(String uuid) {
        return CustomerSize.builder()
            .uuid(uuid)
            .weight(weight)
            .height(height)
            .topSize(topSize)
            .bottomSize(bottomSize)
            .footSize(footSize)
            .agreement(agreement)
            .build();
    }

    public CustomerSize updateToEntity(CustomerSize customerSize) {
        return CustomerSize.builder()
            .id(customerSize.getId())  // 기존 엔티티의 id 사용
            .uuid(customerSize.getUuid())
            .weight(weight)
            .height(height)
            .topSize(topSize)
            .bottomSize(bottomSize)
            .footSize(footSize)
            .agreement(agreement)
            .build();
    }
}
