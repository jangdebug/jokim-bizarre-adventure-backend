package com.jokim.sivillage.api.bridge.application;

import com.jokim.sivillage.api.bridge.dto.ProductCategoryListRequestDto;
import com.jokim.sivillage.api.bridge.infrastructure.ProductCategoryListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ProductCategoryListServiceImpl implements ProductCategoryListService {

    private final ProductCategoryListRepository productCategoryListRepository;

    @Override
    public void addProductByCategories(ProductCategoryListRequestDto productCategoryListRequestDto) {
        productCategoryListRepository.save(productCategoryListRequestDto.toEntity());
    }

}
