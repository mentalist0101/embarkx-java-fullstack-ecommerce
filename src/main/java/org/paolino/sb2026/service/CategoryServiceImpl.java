package org.paolino.sb2026.service;

import org.paolino.sb2026.exceptions.ResourceNotFoundException;
import org.paolino.sb2026.model.Category;
import org.paolino.sb2026.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public void createCategory(Category category) {
        categoryRepository.save(category);
    }

    @Override
    public Category updateCategory(Category category, Long categoryId) {
        categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));
        category.setCategoryId(categoryId);
        return categoryRepository.save(category);
    }

    @Override
    public String deleteCategory(Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));
        categoryRepository.delete(category);
        return "Category with categoryId: " + categoryId + " deleted successfully !!";
    }
}