package com.jokim.sivillage.api.category.dto;

import com.jokim.sivillage.api.category.domain.Category;
import com.jokim.sivillage.api.category.vo.in.CreateCategoryRequestVo;
import com.jokim.sivillage.api.category.vo.in.UpdateCategoryRequestVo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoryRequestDto {

    private String parentCategoryCode;
    private String categoryCode;
    private String name;

    public static CategoryRequestDto toDto(CreateCategoryRequestVo createCategoryRequestVo) {
        return CategoryRequestDto.builder()
            .parentCategoryCode(createCategoryRequestVo.getParentCategoryCode())
            .name(createCategoryRequestVo.getName())
            .build();
    }

    public static CategoryRequestDto toDto(UpdateCategoryRequestVo updateCategoryRequestVo) {
        return CategoryRequestDto.builder()
            .categoryCode(updateCategoryRequestVo.getCategoryCode())
            .name(updateCategoryRequestVo.getName())
            .build();
    }

    public Category toEntity(String categoryCode, Category parentCategory) {     // create Entity
        return Category.builder()
            .parentCategory(parentCategory)
            .categoryCode(categoryCode)
            .name(name)
            .build();
    }

    public Category toEntity(Long id, Category parentCategory) {    // update Entity
        return Category.builder()
                .id(id)
                .parentCategory(parentCategory)
                .categoryCode(categoryCode)
                .name(name)
                .build();
    }

}
