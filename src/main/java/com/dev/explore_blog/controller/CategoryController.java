package com.dev.explore_blog.controller;

import com.dev.explore_blog.payload.CategoryDto;
import com.dev.explore_blog.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/exploreBlog/category")
@Tag(
        name = "Rest APIs for Category"
)
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Operation(summary = "creates new post", description = "used to save posts into the database")
    @ApiResponse(responseCode = "201",description = "HTTP Status 201 CREATED")
    @SecurityRequirement(name = "Bear Authentication")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/add")
    public ResponseEntity<CategoryDto> addCategory(@Valid @RequestBody CategoryDto categoryDto) {
        CategoryDto response = categoryService.addCategory(categoryDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);

    }

    @GetMapping("/getAll")
    public List<CategoryDto> getAllCategory() {
        return categoryService.getAllCategory();
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable Long id) {
        CategoryDto response = categoryService.getCategory(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "creates new post", description = "used to save posts into the database")
    @ApiResponse(responseCode = "201",description = "HTTP Status 201 CREATED")
    @SecurityRequirement(name = "Bear Authentication")
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<CategoryDto> updateCategory(@PathVariable Long id,
                                                      @Valid @RequestBody CategoryDto categoryDto) {
        CategoryDto response = categoryService.updateCategory(id, categoryDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Operation(summary = "creates new post", description = "used to save posts into the database")
    @ApiResponse(responseCode = "201",description = "HTTP Status 201 CREATED")
    @SecurityRequirement(name = "Bear Authentication")
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.ok(" Category deleted successfully! ");
    }

}
