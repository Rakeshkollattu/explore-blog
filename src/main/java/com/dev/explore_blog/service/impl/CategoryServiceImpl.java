package com.dev.explore_blog.service.impl;

import com.dev.explore_blog.entity.Category;
import com.dev.explore_blog.exception.ResourceNotFoundException;
import com.dev.explore_blog.payload.CategoryDto;
import com.dev.explore_blog.repository.CategoryRepository;
import com.dev.explore_blog.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository, ModelMapper modelMapper) {

        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public CategoryDto addCategory(CategoryDto categoryDto) {

        Category category = modelMapper.map(categoryDto, Category.class);
        Category newCategory = categoryRepository.save(category);
        return modelMapper.map(newCategory, CategoryDto.class);
    }

    @Override
    public CategoryDto getCategory(Long id) {

        Category category = categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("category", "id", id));
        return modelMapper.map(category, CategoryDto.class);
    }

    @Override
    public List<CategoryDto> getAllCategory() {

        List<Category> categoryList = categoryRepository.findAll();
        return categoryList.stream().map((category) -> (modelMapper.map(category, CategoryDto.class))).collect(Collectors.toList());
    }

    @Override
    public CategoryDto updateCategory(Long id, CategoryDto contactDto) {

        Category category = categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("category", "id", id));
        category.setId(category.getId());
        category.setName(contactDto.getName());
        category.setDescription(contactDto.getDescription());
        Category updatedCategory = categoryRepository.save(category);
        return modelMapper.map(updatedCategory, CategoryDto.class);


    }

    @Override
    public void deleteCategory(Long id) {

        Category category = categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("category", "id", id));
        categoryRepository.delete(category);
    }
}
