package com.jokim.sivillage.api.customer.vo.in;

import lombok.Getter;

@Getter
public class CustomerSizeRequestVo {

    private Integer weight;
    private Integer height;
    private String topSize;
    private String bottomSize;
    private String footSize;
    private Boolean agreement;

}
