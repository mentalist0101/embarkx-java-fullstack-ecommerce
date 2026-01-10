package org.paolino.sb2026.service;

import org.paolino.sb2026.model.Category;
import org.paolino.sb2026.payload.CategoryResponse;

public interface CategoryService {
    CategoryResponse getAllCategories();
    void createCategory(Category category);
    Category updateCategory(Category category, Long categoryId);
    String deleteCategory(Long categoryId);
}
