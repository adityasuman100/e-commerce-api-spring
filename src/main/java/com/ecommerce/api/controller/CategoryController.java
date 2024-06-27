package com.ecommerce.api.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.api.dto.request.UpdateCategoryReqDto;
import com.ecommerce.api.model.Category;
import com.ecommerce.api.service.CategoryService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {
    final CategoryService categoryService;

    @GetMapping("")
    List<Category> getAllCategories(){
        return categoryService.getAllCategories();
    }

    @GetMapping("/{id}")
    public Category getSingleCategory(@PathVariable String id) throws Exception {
        return categoryService.getSingleCategory(id);
    }
    
    @DeleteMapping("/{id}")
    public String deleteCategory(@PathVariable String id){
        return categoryService.deleteCategory(id);
    }

    @PutMapping("/{id}")
    public Category updateCategory(@PathVariable String id, UpdateCategoryReqDto updateCategoryReqDto) throws Exception {
        return categoryService.updateCategory(updateCategoryReqDto, id);
    }
}
