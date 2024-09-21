package com.jokim.sivillage.api.product.dto.out;

import com.jokim.sivillage.api.product.vo.out.ProductImageResponseVo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductImageResponseDto {

    private String imageUrl;

    public ProductImageResponseVo toVo() {
        return ProductImageResponseVo.builder()
            .imageUrl(imageUrl)
            .build();
    }
}
