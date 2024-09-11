package com.jokim.sivillage.api.category.application;

import static com.jokim.sivillage.common.entity.BaseResponseStatus.ALREADY_EXIST_CATEGORY;
import static com.jokim.sivillage.common.entity.BaseResponseStatus.FAILED_TO_DELETE_CATEGORY;
import static com.jokim.sivillage.common.entity.BaseResponseStatus.FAILED_TO_GENERATE_CATEGORY_CODE;
import static com.jokim.sivillage.common.entity.BaseResponseStatus.FAILED_TO_INSERT_CATEGORY;
import static com.jokim.sivillage.common.entity.BaseResponseStatus.FAILED_TO_UPDATE_CATEGORY;
import static com.jokim.sivillage.common.entity.BaseResponseStatus.NOT_EXIST_CATEGORY;
import static com.jokim.sivillage.common.entity.BaseResponseStatus.NOT_EXIST_CHILD_CATEGORY;
import static com.jokim.sivillage.common.entity.BaseResponseStatus.NOT_EXIST_PARENT_CATEGORY;

import com.jokim.sivillage.api.category.domain.Category;
import com.jokim.sivillage.api.category.dto.CategoryRequestDto;
import com.jokim.sivillage.api.category.dto.CategoryResponseDto;
import com.jokim.sivillage.api.category.infrastructure.CategoryRepository;
import com.jokim.sivillage.common.exception.BaseException;
import com.jokim.sivillage.common.utils.CodeGenerator;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    private static final int MAX_CODE_TRIES = 5;  // 최대 재시도 횟수

    @Transactional
    @Override
    public void createCategory(CategoryRequestDto categoryRequestDto) {

        String parentCategoryCode = categoryRequestDto.getParentCategoryCode();

        // parentCategoryCode가 null이거나 존재해야 함
        if(parentCategoryCode != null && !categoryRepository.existsByCategoryCode(parentCategoryCode)) {
            log.info("Parent Category {} doesn't exist", parentCategoryCode);
            throw new BaseException(NOT_EXIST_PARENT_CATEGORY);
        }

        // parentCategoryCode가 null이면 Category 객체 새로 생성
        Category parentCategory = parentCategoryCode == null ? null :
            categoryRepository.findByCategoryCode(parentCategoryCode).get();

        
        // 같은 (이름, 부모 카테고리) 쌍은 중복이면 안 됨
        if (categoryRepository.existsByNameAndParentCategory(categoryRequestDto.getName(), parentCategory)) {
            log.info("Category with name {} already exists", categoryRequestDto.getName());
            throw new BaseException(ALREADY_EXIST_CATEGORY);
        }

        String categoryCode = generateUniqueCategoryCode();

        try {
            categoryRepository.save(categoryRequestDto.toEntity(categoryCode, parentCategory));
        } catch (IllegalArgumentException e) {
            log.warn("Validation failed: {}", e.getMessage());
            throw e;  // rethrow the exception to be handled by the caller or a global exception handler
        } catch (Exception e) {
            log.error("An unexpected error occurred: ", e);
            throw new BaseException(FAILED_TO_INSERT_CATEGORY);
        }

    }

    @Transactional(readOnly = true)
    @Override
    public List<CategoryResponseDto> getCategories(String parentCategoryCode) {
        List<Category> categories = categoryRepository.findByParentCategoryCategoryCode(parentCategoryCode);

        if(categories.isEmpty()) throw new BaseException(NOT_EXIST_CHILD_CATEGORY);

        return categories.stream().map(CategoryResponseDto::toDto).toList();
    }

    @Transactional
    @Override
    public void updateCategory(CategoryRequestDto categoryRequestDto) {

        String parentCategoryCode = categoryRequestDto.getParentCategoryCode();

        // parentCategoryCode가 null이거나 존재해야 함
        if(parentCategoryCode != null && !categoryRepository.existsByCategoryCode(parentCategoryCode)) {
            log.info("Parent Category {} doesn't exist", parentCategoryCode);
            throw new BaseException(NOT_EXIST_PARENT_CATEGORY);
        }

        // parentCategoryCode가 null이면 Category 객체 새로 생성
        Category parentCategory = parentCategoryCode == null ? null :
            categoryRepository.findByCategoryCode(parentCategoryCode).get();

        Long id = categoryRepository.findByCategoryCode(categoryRequestDto.getCategoryCode()).map(Category::getId)
            .orElseThrow(() -> new BaseException(NOT_EXIST_CATEGORY));

        if (categoryRepository.existsByNameAndParentCategory(categoryRequestDto.getName(), parentCategory)) {
            log.info("Category with name {} already exists", categoryRequestDto.getName());
            throw new BaseException(ALREADY_EXIST_CATEGORY);
        }

        // 자신의 자식이 부모가 되지 않도록 체크 필요

        try {
            categoryRepository.save(categoryRequestDto.toEntity(id, parentCategory));
        } catch (IllegalArgumentException e) {
            log.warn("Validation failed: {}", e.getMessage());
            throw e;  // rethrow the exception to be handled by the caller or a global exception handler
        } catch (Exception e) {
            log.error("An unexpected error occurred: ", e);
            throw new BaseException(FAILED_TO_UPDATE_CATEGORY);
        }

    }

    @Transactional
    @Override
    public void deleteCategory(String categoryCode) {
        Category category = categoryRepository.findByCategoryCode(categoryCode)
            .orElseThrow(() -> new BaseException(NOT_EXIST_CATEGORY));

        if(categoryRepository.existsByParentCategory(category)) throw new BaseException(FAILED_TO_DELETE_CATEGORY);

        categoryRepository.deleteByCategoryCode(categoryCode);
    }

    private String generateUniqueCategoryCode() {
        for (int i = 0; i < MAX_CODE_TRIES; i++) {
            String categoryCode = CodeGenerator.generateCode();

            if(!categoryRepository.existsByCategoryCode(categoryCode)) return categoryCode;
        }
        throw new BaseException(FAILED_TO_GENERATE_CATEGORY_CODE);
    }

}
