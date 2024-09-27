package com.jokim.sivillage.api.product.vo.out;

import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProductOptionResponseVo {
    private List<String> sizeValues;
    private List<String> colorValues;
    private List<String> etcValues;
}
