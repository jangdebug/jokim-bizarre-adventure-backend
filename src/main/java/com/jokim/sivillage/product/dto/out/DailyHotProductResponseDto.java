package com.jokim.sivillage.product.dto.out;

import com.jokim.sivillage.brand.domain.Brand;
import com.jokim.sivillage.product.vo.out.DailyHotProductResponseVo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Setter;

@AllArgsConstructor
@Builder
public class DailyHotProductResponseDto {

    private Long productId;
    private String productName;
    private Brand brandName;
    private String imageUrl;
    private Integer rank;
    private Double price;
    private Integer discountRate;
    private Boolean isWishList;

    public DailyHotProductResponseVo toVo() {
        return DailyHotProductResponseVo.builder()
            .productId(productId)
            .productName(productName)
            .brandName(brandName)
            .imageUrl(imageUrl)
            .rank(rank)
            .price(price)
            .discountRate(discountRate)
            .isWishList(isWishList)
            .build();
    }


}
