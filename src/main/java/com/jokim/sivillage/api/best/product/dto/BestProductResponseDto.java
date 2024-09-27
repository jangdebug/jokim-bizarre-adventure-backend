package com.jokim.sivillage.api.best.product.dto;

import com.jokim.sivillage.api.best.product.domain.BestProduct;
import com.jokim.sivillage.api.best.product.vo.BestProductResponseVo;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BestProductResponseDto {
    private String productCode;
    private Integer rankValue;
    private LocalDateTime updateAt;

    public static BestProductResponseDto toDto(BestProduct bestProduct){

        return BestProductResponseDto.builder()
            .productCode(bestProduct.getProductCode())
            .rankValue(bestProduct.getRankValue())
            .updateAt(bestProduct.getUpdateAt())
            .build();
    }

    public BestProductResponseVo toVo(){
        return BestProductResponseVo.builder()
            .productCode(productCode)
            .rankValue(rankValue)
            .updateAt(updateAt)
            .build();
    }
}
