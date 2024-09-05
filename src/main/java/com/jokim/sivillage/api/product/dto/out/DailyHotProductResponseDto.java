package com.jokim.sivillage.api.product.dto.out;

import com.jokim.sivillage.api.brand.Brand;
import com.jokim.sivillage.api.product.vo.out.DailyHotProductResponseVo;
import lombok.AllArgsConstructor;
import lombok.Builder;

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
