package com.jokim.sivillage.api.bridge.application;

import com.jokim.sivillage.api.bridge.dto.ProductCategoryListRequestDto;
import com.jokim.sivillage.api.bridge.dto.ProductCategoryListResponseDto;
import com.jokim.sivillage.api.bridge.infrastructure.ProductCategoryListRepository;
import com.jokim.sivillage.api.bridge.infrastructure.ProductCategoryListRepositoryCustom;
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

        CursorPage<String> cursor = productCategoryListRepositoryCustom.getProductCategoryListByCategories(
            mainCategoryCode, secondaryCategoryCode, tertiaryCategoryCode, quaternaryCategoryCode,
            lastId, pageSize, pageNo);

        return new CursorPage<>(
            cursor.getContent().stream().map(ProductCategoryListResponseDto::toDto).toList(),
            cursor.getNextCursor(), cursor.getHasNext(), cursor.getPageSize(), cursor.getPageNo());
    }

    @Transactional(readOnly = true)
    @Override
    public CursorPage<ProductCategoryListResponseDto> getProductCodesByOptions(
        String mainCategoryCode, String secondaryCategoryCode,
        String tertiaryCategoryCode, String quaternaryCategoryCode,
        Long lastId, Integer pageSize, Integer pageNo,
        Long sizeId, Long colorId, Long etcId) {

        CursorPage<String> cursor = productCategoryListRepositoryCustom.getProductCodesByOptions(
            mainCategoryCode, secondaryCategoryCode, tertiaryCategoryCode, quaternaryCategoryCode,
            lastId, pageSize, pageNo, sizeId, colorId, etcId);

        return new CursorPage<>(
            cursor.getContent().stream().map(ProductCategoryListResponseDto::toDto).toList(),
            cursor.getNextCursor(), cursor.getHasNext(), cursor.getPageSize(), cursor.getPageNo());
    }

}
