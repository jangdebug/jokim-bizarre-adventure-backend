package com.jokim.sivillage.api.category.infrastructure;

import com.jokim.sivillage.api.category.domain.Category;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    List<Category> findByParentCategoryCode(String parentCategoryCode);
    Optional<Category> findByCategoryCode(String categoryCode);

    boolean existsByNameAndParentCategoryCode(String name, String parentCategoryCode);
    boolean existsByParentCategoryCode(String parentCategoryCode);
    boolean existsByCategoryCode(String categoryCode);

    void deleteByCategoryCode(String categoryCode);

}
