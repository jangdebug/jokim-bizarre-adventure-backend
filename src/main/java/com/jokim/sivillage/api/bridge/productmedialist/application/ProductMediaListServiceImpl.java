package com.jokim.sivillage.api.bridge.productmedialist.application;

import com.jokim.sivillage.api.bridge.productmedialist.domain.ProductMediaList;
import com.jokim.sivillage.api.bridge.productmedialist.dto.in.ProductMediaListRequestDto;
import com.jokim.sivillage.api.bridge.productmedialist.dto.out.AllProductMediaListsResponseDto;
import com.jokim.sivillage.api.bridge.productmedialist.dto.out.ThumbnailProductMediaListResponseDto;
import com.jokim.sivillage.api.bridge.productmedialist.infrastructure.ProductMediaListRepository;
import com.jokim.sivillage.api.bridge.productmedialist.infrastructure.ProductMediaListRepositoryCustom;
import com.jokim.sivillage.common.exception.BaseException;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.jokim.sivillage.common.entity.BaseResponseStatus.ALREADY_EXIST_THUMBNAIL;
import static com.jokim.sivillage.common.entity.BaseResponseStatus.NOT_EXIST_MEDIA;

@RequiredArgsConstructor
@Service
public class ProductMediaListServiceImpl implements ProductMediaListService {

    private final ProductMediaListRepository productMediaListRepository;
    private final ProductMediaListRepositoryCustom productMediaListRepositoryCustom;

    @Transactional
    @Override
    public void addProductMediaList(ProductMediaListRequestDto productMediaListRequestDto) {
        if(productMediaListRequestDto.getIsThumbnail() &&
            productMediaListRepository.existsByProductCodeAndIsThumbnail(
            productMediaListRequestDto.getProductCode(), true))
            throw new BaseException(ALREADY_EXIST_THUMBNAIL);

        productMediaListRepository.save(productMediaListRequestDto.toEntity());
    }

    @Transactional(readOnly = true)
    @Override
    public List<AllProductMediaListsResponseDto> getAllProductMediaLists(String productCode) {
        return productMediaListRepositoryCustom.getAllProductMediaLists(productCode);
    }

    @Transactional(readOnly = true)
    @Override
    public ThumbnailProductMediaListResponseDto getThumbnailByProductCode(String productCode) {
        return productMediaListRepositoryCustom.getThumbnailByProductCode(productCode).orElse(new ThumbnailProductMediaListResponseDto());
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
