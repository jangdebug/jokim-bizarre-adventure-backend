package com.jokim.sivillage.api.category.application;

import static com.jokim.sivillage.common.entity.BaseResponseStatus.ALREADY_EXIST_CATEGORY_NAME;
import static com.jokim.sivillage.common.entity.BaseResponseStatus.FAILED_TO_DELETE_CATEGORY;
import static com.jokim.sivillage.common.entity.BaseResponseStatus.FAILED_TO_GENERATE_CATEGORY_CODE;
import static com.jokim.sivillage.common.entity.BaseResponseStatus.NOT_EXIST_CATEGORY;
import static com.jokim.sivillage.common.entity.BaseResponseStatus.NOT_EXIST_PARENT_CATEGORY;

import com.jokim.sivillage.api.category.domain.Category;
import com.jokim.sivillage.api.category.dto.CategoryRequestDto;
import com.jokim.sivillage.api.category.dto.CategoryResponseDto;
import com.jokim.sivillage.api.category.infrastructure.CategoryRepository;
import com.jokim.sivillage.common.exception.BaseException;
import com.jokim.sivillage.common.utils.CodeGenerator;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    private static final int MAX_CODE_TRIES = 5;  // 최대 재시도 횟수

    @Transactional
    @Override
    public void createCategory(CategoryRequestDto categoryRequestDto) {

        String parentCategoryCode = categoryRequestDto.getParentCategoryCode();

        Category parentCategory = parentCategoryCode == null ? null :
            categoryRepository.findByCategoryCode(parentCategoryCode)
                .orElseThrow(() -> new BaseException(NOT_EXIST_PARENT_CATEGORY));

        // 유니크 제약조건으로 체크하면 parent_category_id가 null일 때 중복 이름을 허용하게 되므로 별도의 exception 처리
        if(categoryRepository.existsByNameAndParentCategory(categoryRequestDto.getName(),
            parentCategory)) throw new BaseException(ALREADY_EXIST_CATEGORY_NAME);

        String categoryCode = generateUniqueCategoryCode();

        categoryRepository.save(categoryRequestDto.toEntity(categoryCode, parentCategory));

    }

    @Transactional(readOnly = true)
    @Override
    public List<CategoryResponseDto> getSubcategories(String parentCategoryCode) {

        List<Category> categories = categoryRepository.findByParentCategoryCategoryCode(
            parentCategoryCode);

        return categories.stream().map(CategoryResponseDto::toDto).toList();
    }

    @Transactional
    @Override
    public void updateCategory(CategoryRequestDto categoryRequestDto) {

        Category category = categoryRepository.findByCategoryCode(categoryRequestDto.getCategoryCode())
            .orElseThrow(() -> new BaseException(NOT_EXIST_CATEGORY));

        // 유니크 제약조건으로 체크하면 parent_category_id가 null일 때 중복 이름을 허용하게 되므로 별도의 exception 처리
        if(categoryRepository.existsByNameAndParentCategory(categoryRequestDto.getName(), category.getParentCategory()))
            throw new BaseException(ALREADY_EXIST_CATEGORY_NAME);

        categoryRepository.save(categoryRequestDto.toEntity(category.getId(), category.getParentCategory()));

    }

    @Transactional
    @Override
    public void deleteCategory(String categoryCode) {
        Category category = categoryRepository.findByCategoryCode(categoryCode)
            .orElseThrow(() -> new BaseException(NOT_EXIST_CATEGORY));

        // 하위 카테고리 존재하면 삭제 불가
        if (categoryRepository.existsByParentCategory(category)) throw new BaseException(FAILED_TO_DELETE_CATEGORY);

        categoryRepository.deleteByCategoryCode(categoryCode);
    }

    private String generateUniqueCategoryCode() {
        for (int i = 0; i < MAX_CODE_TRIES; i++) {
            String categoryCode = CodeGenerator.generateCode("CTG");

            if (!categoryRepository.existsByCategoryCode(categoryCode)) return categoryCode;
        }

        throw new BaseException(FAILED_TO_GENERATE_CATEGORY_CODE);
    }

}
