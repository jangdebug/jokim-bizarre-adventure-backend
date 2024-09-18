package com.jokim.sivillage.api.bridge.dto.in;

import com.jokim.sivillage.api.bridge.domain.ProductMediaList;
import com.jokim.sivillage.api.bridge.vo.in.ProductMediaListRequestVo;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProductMediaListRequestDto {

    private String productCode;
    private String mediaCode;

    public static ProductMediaListRequestDto toDto(
            ProductMediaListRequestVo productMediaListRequestVo) {

        return ProductMediaListRequestDto.builder()
                .productCode(productMediaListRequestVo.getProductCode())
                .mediaCode(productMediaListRequestVo.getMediaCode())
                .build();
    }

    public ProductMediaList toEntity(Boolean isThumbnail) { // add ProductMediaList
        return ProductMediaList.builder()
                .productCode(productCode)
                .mediaCode(mediaCode)
                .isThumbnail(isThumbnail)
                .build();
    }

    public ProductMediaList toEntity(Long id, Boolean isThumbnail) { // update newThumbnail-ProductMediaList
        return ProductMediaList.builder()
                .id(id)
                .productCode(productCode)
                .mediaCode(mediaCode)
                .isThumbnail(isThumbnail)
                .build();
    }

    public ProductMediaList toEntity(Long id, String mediaCode, Boolean isThumbnail) { // update oldThumbnail-ProductMediaList
        return ProductMediaList.builder()
                .id(id)
                .productCode(productCode)
                .mediaCode(mediaCode)
                .isThumbnail(isThumbnail)
                .build();
    }

}
