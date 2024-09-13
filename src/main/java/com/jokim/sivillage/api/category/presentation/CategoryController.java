package com.jokim.sivillage.api.category.presentation;


import com.jokim.sivillage.api.category.application.CategoryService;
import com.jokim.sivillage.api.category.dto.CategoryRequestDto;
import com.jokim.sivillage.api.category.dto.CategoryResponseDto;
import com.jokim.sivillage.api.category.vo.in.CreateCategoryRequestVo;
import com.jokim.sivillage.api.category.vo.in.UpdateCategoryRequestVo;
import com.jokim.sivillage.api.category.vo.out.GetCategoryResponseVo;
import com.jokim.sivillage.common.entity.BaseResponse;
import com.jokim.sivillage.common.entity.CommonResponseMessage;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    public BaseResponse<Void> createCategory(@RequestBody CreateCategoryRequestVo createCategoryRequestVo) {

        log.info("categoryRequestVo: {}", createCategoryRequestVo);
        categoryService.createCategory(CategoryRequestDto.toDto(createCategoryRequestVo));

        return new BaseResponse<>();
    }

    @GetMapping
    public BaseResponse<List<GetCategoryResponseVo>> getCategories(
        @RequestParam(value = "parentCategoryCode", required = false) String parentCategoryCode) {

        log.info("parentCategoryCode : {}", parentCategoryCode);

        return new BaseResponse<>(
            categoryService.getCategories(parentCategoryCode).stream().map(CategoryResponseDto::toVo)
                .toList());
    }

    @PutMapping
    public BaseResponse<Void> updateCategory(@RequestBody UpdateCategoryRequestVo updateCategoryRequestVo) {

        log.info("categoryRequestVo: {}", updateCategoryRequestVo);
        categoryService.updateCategory(CategoryRequestDto.toDto(updateCategoryRequestVo));

        return new BaseResponse<>();
    }

    @DeleteMapping("/{categoryCode}")
    public BaseResponse<Void> deleteCategory(@PathVariable("categoryCode") String categoryCode) {

        log.info("categoryCode: {}", categoryCode);
        categoryService.deleteCategory(categoryCode);

        return new BaseResponse<>();
    }


}
