package com.jokim.sivillage.api.bridge.infrastructure;

import com.jokim.sivillage.common.utils.CursorPage;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductCategoryListRepositoryCustom {

    CursorPage<String> getProductCategoryListByCategories(
        String mainCategoryCode, String secondaryCategoryCode,
        String tertiaryCategoryCode, String quaternaryCategoryCode,
        Long lastId, Integer pageSize, Integer pageNo
    );

    CursorPage<String> getProductCodesByOptions(
        String mainCategoryCode, String secondaryCategoryCode,
        String tertiaryCategoryCode, String quaternaryCategoryCode,
        Long lastId, Integer pageSize, Integer pageNo,
        Long sizeId, Long colorId, Long etcId);

}
