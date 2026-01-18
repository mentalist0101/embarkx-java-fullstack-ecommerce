package org.paolino.sb2026.services;

import org.paolino.sb2026.payloads.CategoryDTO;
import org.paolino.sb2026.payloads.CategoryResponse;

public interface CategoryService {
    CategoryResponse getAllCategories(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder);
    CategoryDTO createCategory(CategoryDTO categoryDTO);
    CategoryDTO updateCategory(CategoryDTO categoryDTO, Long categoryId);
    CategoryDTO deleteCategory(Long categoryId);
}
