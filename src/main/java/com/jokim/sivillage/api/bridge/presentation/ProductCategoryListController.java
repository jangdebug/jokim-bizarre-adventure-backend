package com.jokim.sivillage.api.bridge.presentation;

import com.jokim.sivillage.api.bridge.application.ProductCategoryListService;
import com.jokim.sivillage.api.bridge.dto.ProductCategoryListRequestDto;
import com.jokim.sivillage.api.bridge.vo.AddProductCategoryListRequestVo;
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
@RequestMapping("/v1/bridge")
public class ProductCategoryListController {

    private final ProductCategoryListService productCategoryListService;

    @Operation(summary = "Product-Category-List 생성 API", description = "Product 생성 API가 해당 서비스를 함께 처리합니다")
    @PostMapping("/product-category-list")
    public BaseResponse<Void> addProductByCategories(
            @RequestBody AddProductCategoryListRequestVo productCategoryListRequestVo) {

        productCategoryListService.addProductByCategories(ProductCategoryListRequestDto.toDto(productCategoryListRequestVo));

        return new BaseResponse<>();
    }

}
