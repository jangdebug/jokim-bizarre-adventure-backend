package com.jokim.sivillage.api.category.application;

import com.jokim.sivillage.api.category.dto.CategoryRequestDto;
import com.jokim.sivillage.api.category.dto.CategoryResponseDto;
import java.util.List;

public interface CategoryService {

    void createCategory(CategoryRequestDto categoryRequestDto);
    List<CategoryResponseDto> getCategories(String parentCategoryCode);
    void updateCategory(CategoryRequestDto categoryRequestDto);
    void deleteCategory(String categoryCode);

}
