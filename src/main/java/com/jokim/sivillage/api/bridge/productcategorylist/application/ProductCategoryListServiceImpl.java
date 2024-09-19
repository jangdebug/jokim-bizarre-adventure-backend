package com.jokim.sivillage.api.bridge.productcategorylist.application;

import com.jokim.sivillage.api.bridge.productcategorylist.dto.ProductCategoryListRequestDto;
import com.jokim.sivillage.api.bridge.productcategorylist.dto.ProductCategoryListResponseDto;
import com.jokim.sivillage.api.bridge.productcategorylist.infrastructure.ProductCategoryListRepository;
import com.jokim.sivillage.api.bridge.productcategorylist.infrastructure.ProductCategoryListRepositoryCustom;
import com.jokim.sivillage.common.utils.CursorPage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ProductCategoryListServiceImpl implements ProductCategoryListService {

    private final ProductCategoryListRepository productCategoryListRepository;
    private final ProductCategoryListRepositoryCustom productCategoryListRepositoryCustom;

    @Transactional
    @Override
    public void addProductByCategories(
        ProductCategoryListRequestDto productCategoryListRequestDto) {
        productCategoryListRepository.save(productCategoryListRequestDto.toEntity());
    }

    @Transactional(readOnly = true)
    @Override
    public CursorPage<ProductCategoryListResponseDto> getProductCategoryListByCategories(
        String mainCategoryCode, String secondaryCategoryCode,
        String tertiaryCategoryCode, String quaternaryCategoryCode,
        Long lastId, Integer pageSize, Integer pageNo) {

        CursorPage<String> cursorPage = productCategoryListRepositoryCustom.getProductCategoryListByCategories(
            mainCategoryCode, secondaryCategoryCode, tertiaryCategoryCode, quaternaryCategoryCode,
            lastId, pageSize, pageNo);

        return CursorPage.toCursorPage(cursorPage,
            cursorPage.getContent().stream().map(ProductCategoryListResponseDto::toDto).toList());
    }

}
