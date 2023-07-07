package com.blog.blogapplication.services;

import com.blog.blogapplication.payloads.CategoryDto;

import java.util.List;

public interface CategoryService {

//    Create category
    public CategoryDto createCategory(CategoryDto categoryDto);

//    update Category
    public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId);

//    Get all Category
    public List<CategoryDto> getAllCategories();

//    get
    public CategoryDto getCategory(Integer categoryId);

//    Delete
    public void deleteCategory(Integer categoryId);

}


