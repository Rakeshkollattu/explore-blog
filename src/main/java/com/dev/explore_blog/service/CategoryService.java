package com.dev.explore_blog.service;

import com.dev.explore_blog.payload.CategoryDto;

import java.util.List;

public interface CategoryService {

    CategoryDto addCategory(CategoryDto categoryDto);

    CategoryDto getCategory(Long id);

    List<CategoryDto> getAllCategory();

    CategoryDto updateCategory(Long id, CategoryDto contactDto);

    void deleteCategory(Long id);
}
