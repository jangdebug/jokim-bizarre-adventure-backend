package com.jokim.sivillage.api.product.vo.out;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jokim.sivillage.api.hashtag.domain.Hashtag;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponseVo {

    @JsonProperty("productId")
    private Long id;
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
