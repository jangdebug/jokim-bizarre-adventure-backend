package com.jokim.sivillage.api.category.dto;

import com.jokim.sivillage.api.category.domain.Category;
import com.jokim.sivillage.api.category.vo.out.GetCategoryResponseVo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoryResponseDto {

    private String categoryCode;
    private String name;

    public static CategoryResponseDto toDto(Category category) {
        return CategoryResponseDto.builder()
            .categoryCode(category.getCategoryCode())
            .name(category.getName())
            .build();
    }

    public GetCategoryResponseVo toVo() {
        return GetCategoryResponseVo.builder()
            .categoryCode(categoryCode)
            .name(name)
            .build();
    }

}
