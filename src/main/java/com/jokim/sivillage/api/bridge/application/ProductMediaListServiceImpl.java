package com.jokim.sivillage.api.bridge.application;

import com.jokim.sivillage.api.bridge.domain.ProductMediaList;
import com.jokim.sivillage.api.bridge.dto.ProductMediaListRequestDto;
import com.jokim.sivillage.api.bridge.infrastructure.ProductMediaListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ProductMediaListServiceImpl implements ProductMediaListService {

    private final ProductMediaListRepository productMediaListRepository;

    @Transactional
    @Override
    public void addProductByMedia(ProductMediaListRequestDto productMediaListRequestDto) {
        productMediaListRepository.save(productMediaListRequestDto.toEntity());
    }

}
