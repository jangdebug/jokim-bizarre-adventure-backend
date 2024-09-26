package com.jokim.sivillage.api.bridge.productcategorylist.application;

import static com.jokim.sivillage.common.entity.BaseResponseStatus.NOT_EXIST_IN_PRODUCT_CATEGORY;

import com.jokim.sivillage.api.bridge.productcategorylist.dto.ProductCategoryListRequestDto;
import com.jokim.sivillage.api.bridge.productcategorylist.dto.ProductCategoryListResponseDto;
import com.jokim.sivillage.api.bridge.productcategorylist.infrastructure.ProductCategoryListRepository;
import com.jokim.sivillage.api.bridge.productcategorylist.infrastructure.ProductCategoryListRepositoryCustom;
import com.jokim.sivillage.common.exception.BaseException;
import com.jokim.sivillage.common.utils.CursorPage;
import java.util.List;
import java.util.Optional;
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
    public void addProductByCategories(ProductCategoryListRequestDto productCategoryListRequestDto) {

        Boolean isOnSale = Optional.ofNullable(productCategoryListRequestDto
            .getIsOnSale()).orElse(true);

        productCategoryListRepository.save(productCategoryListRequestDto.toEntity(isOnSale));
    }

    @Transactional(readOnly = true)
    @Override
    public CursorPage<ProductCategoryListResponseDto> getProductCategoryListByCategories(
        String categoryCode, Long lastId, Integer pageSize, Integer pageNo) {

        CursorPage<String> cursorPage = productCategoryListRepositoryCustom.getProductCategoryListByCategories(
            categoryCode, lastId, pageSize, pageNo);

        return CursorPage.toCursorPage(cursorPage,
            cursorPage.getContent().stream().map(ProductCategoryListResponseDto::toDto).toList());
    }

    @Transactional(readOnly = true)
    @Override
    public CursorPage<ProductCategoryListResponseDto> getProductCodesByOptions(
        String mainCategoryCode, String secondaryCategoryCode,
        String tertiaryCategoryCode, String quaternaryCategoryCode,
        Long lastId, Integer pageSize, Integer pageNo,
        Long sizeId, Long colorId, Long etcId) {

        CursorPage<String> cursorPage = productCategoryListRepositoryCustom.getProductCodesByOptions(
            mainCategoryCode, secondaryCategoryCode, tertiaryCategoryCode, quaternaryCategoryCode,
            lastId, pageSize, pageNo, sizeId, colorId, etcId);

        return CursorPage.toCursorPage(cursorPage,
            cursorPage.getContent().stream().map(ProductCategoryListResponseDto::toDto).toList());
    }

    @Transactional
    @Override
    public void updateProductCategoryList(ProductCategoryListRequestDto productCategoryListRequestDto) {
        List<Long> idList = productCategoryListRepositoryCustom.findByProductCodeAndCategoryCodes(
            productCategoryListRequestDto);

        if(idList.isEmpty()) throw new BaseException(NOT_EXIST_IN_PRODUCT_CATEGORY);

        for(Long id : idList) productCategoryListRepository.save(
            productCategoryListRequestDto.toEntity(id));

    }

}
