package com.ecommerce.api.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.ecommerce.api.dto.request.CreateBrandReqDto;
import com.ecommerce.api.dto.request.UpdateBrandReqDto;
import com.ecommerce.api.dto.response.CreateBrandResDto;
import com.ecommerce.api.model.Brand;
import com.ecommerce.api.model.User;
import com.ecommerce.api.repository.BrandRepository;

import lombok.CustomLog;
import lombok.RequiredArgsConstructor;

@Service
@CustomLog
@RequiredArgsConstructor
public class BrandService {
    final BrandRepository brandRepository;
    final ModelMapper modelMapper;

    public CreateBrandResDto createBrand(CreateBrandReqDto createBrandReqDto){
        User user=(User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Brand brand=modelMapper.map(createBrandReqDto, Brand.class);
        brand.setUser(user);
        Brand createdBrand= brandRepository.insert(brand);
        return modelMapper.map(createdBrand, CreateBrandResDto.class);
    }

    public List<Brand> getAllBrands(){
        return brandRepository.findAll();
    }

    public Brand getSingleBrand(String id) throws Exception{
        Optional<Brand> existingBrand= brandRepository.findById(id);
        if(existingBrand.isPresent()){
            return existingBrand.get();
        }
        throw new Exception("Brand not found.");
    }

    public Brand updateBrand(UpdateBrandReqDto updateBrandReqDto, String id) throws Exception{
        Optional<Brand> existingBrand=brandRepository.findById(id);
        if(existingBrand.isPresent()){
            existingBrand.get().setName(updateBrandReqDto.getName());
            brandRepository.save(existingBrand.get());
        }
        throw new Exception("Brand does not exist");
    }

    public String deleteBrand(String id){
        brandRepository.deleteById(id);
        return "Brand deleted successfully.";
    }
}
