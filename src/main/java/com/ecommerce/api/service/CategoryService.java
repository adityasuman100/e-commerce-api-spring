package com.ecommerce.api.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.ecommerce.api.dto.request.CreateCategoryReqDto;
import com.ecommerce.api.dto.request.UpdateCategoryReqDto;
import com.ecommerce.api.dto.response.CreateCategoryResDto;
import com.ecommerce.api.model.Category;
import com.ecommerce.api.repository.CategoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryService {
    final CategoryRepository categoryRepository;
    final ModelMapper modelMapper;

    public CreateCategoryResDto createCategory(CreateCategoryReqDto createCategoryReqDto){
        Category category=modelMapper.map(createCategoryReqDto, Category.class);
        Category createdCategory= categoryRepository.insert(category);
        return modelMapper.map(createdCategory, CreateCategoryResDto.class);
    }

    public List<Category> getAllCategories(){
        return categoryRepository.findAll();
    }

    public Category getSingleCategory(String id) throws Exception{
        Optional<Category> existingCategory=categoryRepository.findById(id);
        if(existingCategory.isPresent()){
            return existingCategory.get();
        }
        throw new Exception("Category does not exist");
    }

    public Category updateCategory(UpdateCategoryReqDto updateCategoryReqDto, String id)throws Exception{
        Optional<Category> existingCategory= categoryRepository.findById(id);
        if(existingCategory.isPresent()){
            existingCategory.get().setName(updateCategoryReqDto.getName());
            return categoryRepository.save(existingCategory.get());
        }
        throw new Exception("Category does not exist.");
    }

    public String deleteCategory(String id){
        categoryRepository.deleteById(id);
        return "category deleted successfully";
    }
}
