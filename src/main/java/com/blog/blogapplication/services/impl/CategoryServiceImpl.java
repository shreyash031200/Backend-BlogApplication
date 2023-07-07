package com.blog.blogapplication.services.impl;

import com.blog.blogapplication.entities.*;
import com.blog.blogapplication.exceptions.ResourceNotFoundException;
import com.blog.blogapplication.payloads.CategoryDto;
import com.blog.blogapplication.respositories.CategoryRepo;
import com.blog.blogapplication.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category cat = this.modelMapper.map(categoryDto, Category.class);
        Category save = this.categoryRepo.save(cat);
        return this.modelMapper.map(save, CategoryDto.class);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
        Category cat = this.categoryRepo.findById(categoryId).orElseThrow(() ->
                new ResourceNotFoundException("Category", " Category ID", categoryId));
        cat.setCategoryTitle(categoryDto.getCategoryTitle());
        cat.setCategoryDescription(categoryDto.getCategoryDescription());
        Category updatedCat = this.categoryRepo.save(cat);

        return this.modelMapper.map(updatedCat, CategoryDto.class);
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        List<Category> allCat = this.categoryRepo.findAll();
        List<CategoryDto> catDto = allCat.stream().map((cat) -> this.modelMapper.map(cat, CategoryDto.class)).
                collect(Collectors.toList());
        return catDto;
    }

    @Override
    public CategoryDto getCategory(Integer categoryId) {
        Category cat = this.categoryRepo.findById(categoryId).orElseThrow(() ->
                new ResourceNotFoundException("Category", " Category ID", categoryId));
        return this.modelMapper.map(cat, CategoryDto.class);
    }

    @Override
    public void deleteCategory(Integer categoryId) {
        Category cat = this.categoryRepo.findById(categoryId).orElseThrow(() ->
                new ResourceNotFoundException("Category", " Category ID", categoryId));
        this.categoryRepo.delete(cat);
    }
}
