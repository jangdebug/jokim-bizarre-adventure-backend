package com.jokim.sivillage.api.category.infrastructure;

import com.jokim.sivillage.api.category.domain.Category;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    List<Category> findByParentCategoryCategoryCode(String parentCategoryCode);
    Optional<Category> findByCategoryCode(String categoryCode);

    boolean existsByNameAndParentCategory(String name, Category parentCategory);
    boolean existsByParentCategory(Category parentCategory);
    boolean existsByCategoryCode(String categoryCode);

    void deleteByCategoryCode(String categoryCode);

}
