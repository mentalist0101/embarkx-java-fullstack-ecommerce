package org.paolino.sb2026.service;

import org.paolino.sb2026.model.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getAllCategories();
    void createCategory(Category category);
    Category updateCategory(Category category, Long categoryId);
    String deleteCategory(Long categoryId);
}
