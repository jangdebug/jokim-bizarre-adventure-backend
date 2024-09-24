package com.jokim.sivillage.api.customer.vo.out;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SizeResponsVo {
    private Integer weight;
    private Integer height;
    private String topSize;
    private String bottomSize;
    private String footSize;
    private Boolean agreement;
}
