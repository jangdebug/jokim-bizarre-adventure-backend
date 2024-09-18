package com.jokim.sivillage.api.bridge.application;

import com.jokim.sivillage.api.bridge.domain.ProductMediaList;
import com.jokim.sivillage.api.bridge.dto.in.ProductMediaListRequestDto;
import com.jokim.sivillage.api.bridge.dto.out.ProductMediaListResponseDto;
import com.jokim.sivillage.api.bridge.infrastructure.ProductMediaListRepository;
import com.jokim.sivillage.common.exception.BaseException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.jokim.sivillage.common.entity.BaseResponseStatus.NOT_EXIST_MEDIA;

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

    @Transactional
    @Override
    public void updateProductMediaList(ProductMediaListRequestDto productMediaListRequestDto) {
        Long newThumbnailMediaId = productMediaListRepository.findByProductCodeAndMediaCode(
                        productMediaListRequestDto.getProductCode(), productMediaListRequestDto.getMediaCode())
                .orElseThrow(() -> new BaseException(NOT_EXIST_MEDIA)).getId();

        // 해당 productCode에 isThumbnail = true인 Data가 2개 이상인 경우를 대비하여 List로 받음
        List<ProductMediaList> oldThumbnailMediaList = productMediaListRepository.findByProductCodeAndIsThumbnail(
                        productMediaListRequestDto.getProductCode(), true);

        for(ProductMediaList oldThumbnailMedia : oldThumbnailMediaList) {
            productMediaListRepository.save(
                    productMediaListRequestDto.toEntity(
                            oldThumbnailMedia.getId(), oldThumbnailMedia.getMediaCode(), false));
        }

        productMediaListRepository.save(
                productMediaListRequestDto.toEntity(newThumbnailMediaId, true));
    }

}
