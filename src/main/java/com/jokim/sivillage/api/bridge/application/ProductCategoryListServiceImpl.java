package com.jokim.sivillage.api.bridge.application;

import com.jokim.sivillage.api.bridge.dto.ProductCategoryListRequestDto;
import com.jokim.sivillage.api.bridge.dto.ProductCategoryListResponseDto;
import com.jokim.sivillage.api.bridge.infrastructure.ProductCategoryListRepository;
import com.jokim.sivillage.api.bridge.infrastructure.ProductCategoryListRepositoryCustom;
import com.jokim.sivillage.common.utils.CursorPage;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ProductCategoryListServiceImpl implements ProductCategoryListService {

    private final ProductCategoryListRepository productCategoryListRepository;
    private final ProductCategoryListRepositoryCustom productCategoryListRepositoryCustom;

    @Transactional
    @Override
    public void addProductByCategories(ProductCategoryListRequestDto productCategoryListRequestDto) {
        productCategoryListRepository.save(productCategoryListRequestDto.toEntity());
    }

    @Override
    public CursorPage<ProductCategoryListResponseDto> getProductCategoryListByCategories(
            String mainCategoryCode, String secondaryCategoryCode,
            String tertiaryCategoryCode, String quaternaryCategoryCode,
            Long lastId, Integer pageSize, Integer pageNo) {

        CursorPage<String> cursor = productCategoryListRepositoryCustom.getProductCategoryListByCategories(
                mainCategoryCode, secondaryCategoryCode, tertiaryCategoryCode, quaternaryCategoryCode,
                lastId, pageSize, pageNo);

        return new CursorPage<>(cursor.getContent().stream().map(ProductCategoryListResponseDto::toDto).toList(),
                cursor.getNextCursor(), cursor.getHasNext(), cursor.getPageSize(), cursor.getPageNo());
    }

}
