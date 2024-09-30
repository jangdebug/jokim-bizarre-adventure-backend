package com.jokim.sivillage.api.mostViewProduct.vo;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MostViewProductVo {
    private String productCode;
    private Integer rankValue;
    private String productName;
    private Double discountPrice;
    private Integer discountRate;
    private String brandCode;
    private String englishName;
    private LocalDateTime updateAt;
}
