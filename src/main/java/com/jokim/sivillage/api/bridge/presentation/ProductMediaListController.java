package com.jokim.sivillage.api.bridge.presentation;

import com.jokim.sivillage.api.bridge.application.ProductMediaListService;
import com.jokim.sivillage.api.bridge.dto.ProductMediaListRequestDto;
import com.jokim.sivillage.api.bridge.vo.AddProductMediaListRequestVo;
import com.jokim.sivillage.common.entity.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Bridge")
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/bridge/product-media-list")
public class ProductMediaListController {

    private final ProductMediaListService productMediaListService;

    @Operation(summary = "Product-Media-List 생성 API")
    @PostMapping
    public BaseResponse<Void> addProductByMedia(
            @RequestBody AddProductMediaListRequestVo addProductMediaListRequestVo) {

        productMediaListService.addProductByMedia(ProductMediaListRequestDto.toDto(addProductMediaListRequestVo));
        return new BaseResponse<>();
    }

}
