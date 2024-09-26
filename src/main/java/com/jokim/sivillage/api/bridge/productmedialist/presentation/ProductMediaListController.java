package com.jokim.sivillage.api.bridge.productmedialist.presentation;

import com.jokim.sivillage.api.bridge.productmedialist.application.ProductMediaListService;
import com.jokim.sivillage.api.bridge.productmedialist.dto.in.ProductMediaListRequestDto;
import com.jokim.sivillage.api.bridge.productmedialist.dto.out.AllProductMediaListsResponseDto;
import com.jokim.sivillage.api.bridge.productmedialist.dto.out.ThumbnailProductMediaListResponseDto;
import com.jokim.sivillage.api.bridge.productmedialist.vo.in.AddProductMediaListRequestVo;
import com.jokim.sivillage.api.bridge.productmedialist.vo.in.UpdateProductMediaListRequestVo;
import com.jokim.sivillage.api.bridge.productmedialist.vo.out.GetAllProductMediaListsResponseVo;
import com.jokim.sivillage.api.bridge.productmedialist.vo.out.GetThumbnailProductMediaListResponseVo;
import com.jokim.sivillage.common.entity.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Product-Media")
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/product-media")
public class ProductMediaListController {

    private final ProductMediaListService productMediaListService;

    @Operation(summary = "Product-Media-List 생성 API")
    @PostMapping
    public BaseResponse<Void> addProductMediaList(
            @RequestBody AddProductMediaListRequestVo addProductMediaListRequestVo) {

        productMediaListService.addProductMediaList(ProductMediaListRequestDto.toDto(
            addProductMediaListRequestVo));
        return new BaseResponse<>();
    }

    @Operation(summary = "Product-Media-List 전체 조회 API", description = "Media 테이블과 RightJoin 하여 Id 오름차순 조회")
    @GetMapping("/{productCode}")
    public BaseResponse<List<GetAllProductMediaListsResponseVo>> getAllProductMediaLists(@PathVariable String productCode) {

        return new BaseResponse<>(
                productMediaListService.getAllProductMediaLists(productCode)
                        .stream().map(AllProductMediaListsResponseDto::toVo).toList());
    }

    @Operation(summary = "Product-Media-List 썸네일 조회 API")
    @GetMapping("/thumbnail/{productCode}")
    public BaseResponse<GetThumbnailProductMediaListResponseVo> getThumbnailByProductCode(
        @PathVariable String productCode) {

        return new BaseResponse<>(productMediaListService.getThumbnailByProductCode(
            productCode).toVo());
    }

    @Operation(summary = "Product-Media-List 썸네일 수정 API")
    @PutMapping
    public BaseResponse<Void> updateProductMediaList(
            @RequestBody UpdateProductMediaListRequestVo updateProductMediaListRequestVo) {

        productMediaListService.updateProductMediaList(ProductMediaListRequestDto.toDto(
            updateProductMediaListRequestVo));
        return new BaseResponse<>();
    }

}
