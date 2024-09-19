package com.jokim.sivillage.api.bridge.productcategorylist.presentation;

import com.jokim.sivillage.api.bridge.productcategorylist.application.ProductCategoryListService;
import com.jokim.sivillage.api.bridge.productcategorylist.dto.ProductCategoryListRequestDto;
import com.jokim.sivillage.api.bridge.productcategorylist.dto.ProductCategoryListResponseDto;
import com.jokim.sivillage.api.bridge.productcategorylist.vo.AddProductCategoryListRequestVo;
import com.jokim.sivillage.api.bridge.productcategorylist.vo.GetProductCategoryListResponseVo;
import com.jokim.sivillage.common.entity.BaseResponse;
import com.jokim.sivillage.common.utils.CursorPage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Bridge")
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/product-category")
public class ProductCategoryListController {

    private final ProductCategoryListService productCategoryListService;

    @Operation(summary = "Product-Category-List 생성 API", description = "Add Product By Categories")
    @PostMapping
    public BaseResponse<Void> addProductByCategories(
            @RequestBody AddProductCategoryListRequestVo addProductCategoryListRequestVo) {

        productCategoryListService.addProductByCategories(ProductCategoryListRequestDto.toDto(addProductCategoryListRequestVo));

        return new BaseResponse<>();
    }

    @Operation(summary = "Product-Category-List 조회 API", description = "Get Product-Category-List By Categories")
    @GetMapping
    public BaseResponse<CursorPage<GetProductCategoryListResponseVo>> getProductCategoryList(
            @RequestParam(value = "mainCategoryCode", required = false) String mainCategoryCode,
            @RequestParam(value = "secondaryCategoryCode", required = false) String secondaryCategoryCode,
            @RequestParam(value = "tertiaryCategoryCode", required = false) String tertiaryCategoryCode,
            @RequestParam(value = "quaternaryCategoryCode", required = false) String quaternaryCategoryCode,
            @RequestParam(value = "lastId", required = false) Long lastId,
            @RequestParam(value = "pageSize", required = false) Integer pageSize,
            @RequestParam(value = "pageNo", required = false) Integer pageNo
    ) {
        CursorPage<ProductCategoryListResponseDto> cursor = productCategoryListService.getProductCategoryListByCategories(
                mainCategoryCode, secondaryCategoryCode, tertiaryCategoryCode, quaternaryCategoryCode,
                lastId, pageSize, pageNo);

        return new BaseResponse<>(
                new CursorPage<>(cursor.getContent().stream().map(ProductCategoryListResponseDto::toVo).toList(),
                        cursor.getNextCursor(), cursor.getHasNext(), cursor.getPageSize(), cursor.getPageNo()));
    }

}
