package com.jokim.sivillage.api.bridge.presentation;

import com.jokim.sivillage.api.bridge.application.ProductMediaListService;
import com.jokim.sivillage.api.bridge.dto.in.ProductMediaListRequestDto;
import com.jokim.sivillage.api.bridge.dto.out.ProductMediaListResponseDto;
import com.jokim.sivillage.api.bridge.vo.in.AddProductMediaListRequestVo;
import com.jokim.sivillage.api.bridge.vo.out.GetProductMediaListResponseVo;
import com.jokim.sivillage.common.entity.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Bridge")
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/bridge/product-media-list")
public class ProductMediaListController {

    private final ProductMediaListService productMediaListService;

    @Operation(summary = "Product-Media-List 생성 API")
    @PostMapping
    public BaseResponse<Void> addProductMediaList(
            @RequestBody AddProductMediaListRequestVo addProductMediaListRequestVo) {

        productMediaListService.addProductMediaList(ProductMediaListRequestDto.toDto(addProductMediaListRequestVo));
        return new BaseResponse<>();
    }

    @Operation(summary = "Product-Media-List 조회 API")
    @GetMapping("/{productCode}")
    public BaseResponse<List<GetProductMediaListResponseVo>> getProductMediaList(@PathVariable String productCode) {

        return new BaseResponse<>(
                productMediaListService.getProductMediaList(productCode)
                        .stream().map(ProductMediaListResponseDto::toVo).toList());
    }

}
