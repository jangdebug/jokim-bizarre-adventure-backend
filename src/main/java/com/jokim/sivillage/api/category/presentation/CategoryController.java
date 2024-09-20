package com.jokim.sivillage.api.category.presentation;


import com.jokim.sivillage.api.category.application.CategoryService;
import com.jokim.sivillage.api.category.dto.CategoryRequestDto;
import com.jokim.sivillage.api.category.dto.CategoryResponseDto;
import com.jokim.sivillage.api.category.vo.in.CreateCategoryRequestVo;
import com.jokim.sivillage.api.category.vo.in.UpdateCategoryRequestVo;
import com.jokim.sivillage.api.category.vo.out.GetCategoryResponseVo;
import com.jokim.sivillage.common.entity.BaseResponse;

import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Category")
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/category")
public class CategoryController {

    private final CategoryService categoryService;

    @Operation(summary = "Category 생성 API")
    @PostMapping
    public BaseResponse<Void> createCategory(
        @RequestBody CreateCategoryRequestVo createCategoryRequestVo) {

        categoryService.createCategory(CategoryRequestDto.toDto(createCategoryRequestVo));

        return new BaseResponse<>();
    }

    @Operation(summary = "자식 Category List 조회 API")
    @GetMapping("/subcategories")
    public BaseResponse<List<GetCategoryResponseVo>> getSubcategories(
        @RequestParam(value = "parentCategoryCode", required = false) String parentCategoryCode) {

        return new BaseResponse<>(
            categoryService.getSubcategories(parentCategoryCode).stream()
                .map(CategoryResponseDto::toVo).toList());
    }

    @Operation(summary = "Category 이름 수정 API")
    @PutMapping
    public BaseResponse<Void> updateCategory(
        @RequestBody UpdateCategoryRequestVo updateCategoryRequestVo) {

        categoryService.updateCategory(CategoryRequestDto.toDto(updateCategoryRequestVo));

        return new BaseResponse<>();
    }

    @Operation(summary = "Category 삭제 API")
    @DeleteMapping("/{categoryCode}")
    public BaseResponse<Void> deleteCategory(@PathVariable("categoryCode") String categoryCode) {

        categoryService.deleteCategory(categoryCode);

        return new BaseResponse<>();
    }


}
