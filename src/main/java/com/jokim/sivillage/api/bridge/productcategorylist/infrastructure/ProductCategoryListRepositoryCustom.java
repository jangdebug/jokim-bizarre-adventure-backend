package com.jokim.sivillage.api.bridge.productcategorylist.infrastructure;

import com.jokim.sivillage.api.bridge.productcategorylist.dto.ProductCategoryListRequestDto;
import com.jokim.sivillage.common.utils.CursorPage;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductCategoryListRepositoryCustom {

    CursorPage<String> getProductCategoryListByCategories(
            String categoryCode, Long lastId, Integer pageSize, Integer pageNo);

    CursorPage<String> getProductCodesByOptions(
        String mainCategoryCode, String secondaryCategoryCode,
        String tertiaryCategoryCode, String quaternaryCategoryCode,
        Long lastId, Integer pageSize, Integer pageNo,
        Long sizeId, Long colorId, Long etcId);

    List<Long> findByProductCodeAndCategoryCodes(ProductCategoryListRequestDto requestDto);

}
