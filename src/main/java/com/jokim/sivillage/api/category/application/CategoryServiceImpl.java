package com.jokim.sivillage.api.category.application;

import static com.jokim.sivillage.common.entity.BaseResponseStatus.ALREADY_EXIST_CATEGORY;
import static com.jokim.sivillage.common.entity.BaseResponseStatus.FAILED_TO_DELETE_CATEGORY;
import static com.jokim.sivillage.common.entity.BaseResponseStatus.FAILED_TO_GENERATE_CATEGORY_CODE;
import static com.jokim.sivillage.common.entity.BaseResponseStatus.FAILED_TO_INSERT_CATEGORY;
import static com.jokim.sivillage.common.entity.BaseResponseStatus.FAILED_TO_UPDATE_CATEGORY;
import static com.jokim.sivillage.common.entity.BaseResponseStatus.NO_EXIST_CATEGORY;

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

        // 같은 (이름, 부모 카테고리) 쌍은 중복이면 안 됨
        if (categoryRepository.existsByNameAndParentCategoryCode(categoryRequestDto.getName(), categoryRequestDto.getParentCategoryCode())) {
            log.info("Category with name {} already exists", categoryRequestDto.getName());
            throw new BaseException(ALREADY_EXIST_CATEGORY);
        }

        String categoryCode = generateUniqueCategoryCode();

        try {
            categoryRepository.save(categoryRequestDto.toEntity(categoryCode));
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
        List<Category> categories = categoryRepository.findByParentCategoryCode(parentCategoryCode);

        if(categories.isEmpty()) throw new BaseException(NO_EXIST_CATEGORY);

        return categories.stream().map(CategoryResponseDto::toDto).toList();
    }

    @Transactional
    @Override
    public void updateCategory(CategoryRequestDto categoryRequestDto) {

        Long id = categoryRepository.findByCategoryCode(categoryRequestDto.getCategoryCode()).map(Category::getId)
            .orElseThrow(() -> new BaseException(NO_EXIST_CATEGORY));

        if (categoryRepository.existsByNameAndParentCategoryCode(categoryRequestDto.getName(), categoryRequestDto.getParentCategoryCode())) {
            log.info("Category with name {} already exists", categoryRequestDto.getName());
            throw new BaseException(ALREADY_EXIST_CATEGORY);
        }

        try {
            categoryRepository.save(categoryRequestDto.toEntity(id));
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
        categoryRepository.findByCategoryCode(categoryCode)
            .orElseThrow(() -> new BaseException(NO_EXIST_CATEGORY));

        if(categoryRepository.existsByParentCategoryCode(categoryCode)) throw new BaseException(FAILED_TO_DELETE_CATEGORY);

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
