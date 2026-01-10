package org.paolino.sb2026.service;

import org.paolino.sb2026.model.Category;
import org.paolino.sb2026.payload.CategoryDTO;
import org.paolino.sb2026.payload.CategoryResponse;

public interface CategoryService {
    CategoryResponse getAllCategories();
    CategoryDTO createCategory(CategoryDTO categoryDTO);
    Category updateCategory(Category category, Long categoryId);
    String deleteCategory(Long categoryId);
}
