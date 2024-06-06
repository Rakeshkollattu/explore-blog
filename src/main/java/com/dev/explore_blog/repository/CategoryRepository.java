package com.dev.explore_blog.repository;

import com.dev.explore_blog.entity.Category;
import com.dev.explore_blog.payload.CategoryDto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
