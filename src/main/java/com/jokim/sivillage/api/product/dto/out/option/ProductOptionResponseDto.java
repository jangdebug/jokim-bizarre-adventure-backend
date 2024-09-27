package com.jokim.sivillage.api.product.dto.out.option;

import com.jokim.sivillage.api.product.vo.out.ProductOptionResponseVo;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProductOptionResponseDto {
    private List<String> sizeList;
    private List<String> colorList;
    private List<String> etcList;

    public ProductOptionResponseVo toResponseVo() {
        return ProductOptionResponseVo.builder()
            .sizeValues(sizeList)
            .colorValues(colorList)
            .etcValues(etcList)
            .build();
    }
}
