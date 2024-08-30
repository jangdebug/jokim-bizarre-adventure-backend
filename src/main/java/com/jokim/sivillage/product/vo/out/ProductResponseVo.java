package com.jokim.sivillage.product.vo.out;

import com.jokim.sivillage.hashtag.domain.Hashtag;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class ProductResponseVo {

    private Long productId;
    private String imageUrl;
    private String brandName;
    private String productName;
    private String discountRate;
    private Double amount;
    private Double price;
    private String starPoint;
    private Integer reviewCount;
    private Hashtag hashtag;
    private String detail;


}
