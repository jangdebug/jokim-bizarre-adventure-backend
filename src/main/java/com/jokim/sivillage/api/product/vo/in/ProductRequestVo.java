package com.jokim.sivillage.api.product.vo.in;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
public class ProductRequestVo {

    // 데이터 입력 할 때 제외 사용 될 일이 없겠다.
    private String productCode;
    private String productName;
    private String detail;
    private Double standardPrice;
    private Double discountPrice;
    private String brandCode;

}
