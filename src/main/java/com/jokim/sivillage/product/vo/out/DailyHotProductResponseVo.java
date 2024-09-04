package com.jokim.sivillage.product.vo.out;

import com.jokim.sivillage.brand.domain.Brand;
import lombok.*;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DailyHotProductResponseVo {

    private Long productId;
    private String productName;
    private Brand brandName;
    private String imageUrl;
    private Integer rank;
    private Double price;
    private Integer discountRate;
    private Boolean isWishList;
}
