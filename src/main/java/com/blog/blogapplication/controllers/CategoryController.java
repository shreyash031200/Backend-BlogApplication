package com.blog.blogapplication.controllers;

import com.blog.blogapplication.payloads.ApiResponse;
import com.blog.blogapplication.payloads.CategoryDto;
import com.blog.blogapplication.services.CategoryService;
import com.blog.blogapplication.services.impl.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.blog.blogapplication.services.CategoryService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;
    @PostMapping("/")
    public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto){
        CategoryDto category = this.categoryService.createCategory(categoryDto);
        return new ResponseEntity<>(category, HttpStatus.CREATED);
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto,
                                                      @PathVariable Integer categoryId){
        CategoryDto categoryDto1 = this.categoryService.updateCategory(categoryDto, categoryId);
        return new ResponseEntity<>(categoryDto1,HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<CategoryDto>> getAllCategories(){
        List<CategoryDto> allCategories = this.categoryService.getAllCategories();
        return new ResponseEntity<>(allCategories ,HttpStatus.OK);
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> getCategory(@PathVariable Integer categoryId){
        CategoryDto category = this.categoryService.getCategory(categoryId);
        return new ResponseEntity<>(category,HttpStatus.OK);
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<ApiResponse> deleteUserById(@PathVariable("categoryId") int categoryId){
        this.categoryService.deleteCategory(categoryId);
        return new ResponseEntity<>(new ApiResponse("Sucessfully Deleted",true),HttpStatus.OK);
    }
}
