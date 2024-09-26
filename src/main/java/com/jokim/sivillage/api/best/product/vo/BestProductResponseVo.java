package com.jokim.sivillage.api.best.product.vo;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BestProductResponseVo {
    private String productCode;
    private Integer rankValue;
    private LocalDateTime updateAt;
}
