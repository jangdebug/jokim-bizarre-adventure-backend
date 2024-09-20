package com.jokim.sivillage.api.bridge.productcategorylist.vo;

import lombok.Getter;

@Getter
public class AddProductCategoryListRequestVo {

    private String productCode;
    private String mainCategoryCode;
    private String secondaryCategoryCode;
    private String tertiaryCategoryCode;
    private String quaternaryCategoryCode;
    private Boolean isOnSale;

}
