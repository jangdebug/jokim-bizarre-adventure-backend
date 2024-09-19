package com.jokim.sivillage.api.bridge.productmedialist.dto.in;

import com.jokim.sivillage.api.bridge.productmedialist.domain.ProductMediaList;
import com.jokim.sivillage.api.bridge.productmedialist.vo.in.AddProductMediaListRequestVo;
import com.jokim.sivillage.api.bridge.productmedialist.vo.in.UpdateProductMediaListRequestVo;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProductMediaListRequestDto {

    private String productCode;
    private String mediaCode;
    private Boolean isThumbnail;

    public static ProductMediaListRequestDto toDto(
        AddProductMediaListRequestVo addProductMediaListRequestVo) {

        return ProductMediaListRequestDto.builder()
            .productCode(addProductMediaListRequestVo.getProductCode())
            .mediaCode(addProductMediaListRequestVo.getMediaCode())
            .isThumbnail(addProductMediaListRequestVo.getIsThumbnail())
            .build();
    }

    public static ProductMediaListRequestDto toDto(
            UpdateProductMediaListRequestVo updateProductMediaListRequestVo) {

        return ProductMediaListRequestDto.builder()
                .productCode(updateProductMediaListRequestVo.getProductCode())
                .mediaCode(updateProductMediaListRequestVo.getMediaCode())
                .build();
    }

    public ProductMediaList toEntity() { // add ProductMediaList
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
