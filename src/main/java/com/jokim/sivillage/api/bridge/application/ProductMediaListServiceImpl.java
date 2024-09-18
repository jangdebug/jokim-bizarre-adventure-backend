package com.jokim.sivillage.api.bridge.application;

import com.jokim.sivillage.api.bridge.dto.in.ProductMediaListRequestDto;
import com.jokim.sivillage.api.bridge.dto.out.ProductMediaListResponseDto;
import com.jokim.sivillage.api.bridge.infrastructure.ProductMediaListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ProductMediaListServiceImpl implements ProductMediaListService {

    private final ProductMediaListRepository productMediaListRepository;

    @Transactional
    @Override
    public void addProductMediaList(ProductMediaListRequestDto productMediaListRequestDto) {
        boolean isFirstMedia = !productMediaListRepository.existsByProductCode(
                productMediaListRequestDto.getProductCode());

        productMediaListRepository.save(productMediaListRequestDto.toEntity(isFirstMedia));
    }

    @Transactional(readOnly = true)
    @Override
    public List<ProductMediaListResponseDto> getProductMediaList(String productCode) {
        return productMediaListRepository.findByProductCode(productCode)
                .stream().map(ProductMediaListResponseDto::toDto).toList();
    }

}
