package com.ecommerce.api.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.api.dto.request.CreateBrandReqDto;
import com.ecommerce.api.dto.request.UpdateBrandReqDto;
import com.ecommerce.api.dto.response.CreateBrandResDto;
import com.ecommerce.api.model.Brand;
import com.ecommerce.api.service.BrandService;

import lombok.RequiredArgsConstructor;



@RestController
@RequestMapping("/api/v1/brands")
@RequiredArgsConstructor
public class BrandController {

    final BrandService brandService;

    @PostMapping("")
    public CreateBrandResDto
    createBrand(@RequestBody CreateBrandReqDto createBrandReqDto) {
        return brandService.createBrand(createBrandReqDto);
    }

    @GetMapping("")
    public List<Brand> getAllBrands() {
        return brandService.getAllBrands();
    }
    
    @GetMapping("/{id}")
    public Brand getSingleBrand(@PathVariable String id) throws Exception {
        return brandService.getSingleBrand(id);
    }

    @DeleteMapping("/{id}")
    public String deleteBrand(@PathVariable String id){
        return brandService.deleteBrand(id);
    }

    @PutMapping("/{id}")
    public Brand updateBrand(@PathVariable String id, UpdateBrandReqDto updateBrandReqDto) throws Exception {
        return brandService.updateBrand(updateBrandReqDto, id);
    }
    
}
