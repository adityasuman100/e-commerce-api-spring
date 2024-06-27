package com.ecommerce.api.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.ecommerce.api.dto.request.CreateColorReqDto;
import com.ecommerce.api.dto.request.UpdateColorReqDto;
import com.ecommerce.api.model.Color;
import com.ecommerce.api.repository.ColorRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ColorService {
    final ColorRepository colorRepository;
    final ModelMapper modelMapper;

    private Optional<Color> getColorByName(String name){
        List<Color> existingColors=colorRepository.findByName(name);
        if(existingColors.isEmpty()){
            return Optional.of(null);
        }
        return Optional.of(existingColors.get(0));
        
    }

    public Color createColor(CreateColorReqDto createColorReqDto)throws Exception{
        Optional<Color> existingColor=getColorByName(createColorReqDto.getName());
        if(existingColor.isPresent()){
            throw new Exception("Color already exist");
        }
        Color color=modelMapper.map(createColorReqDto, Color.class);
        return colorRepository.insert(color);
    }

    public List<Color> getAllColors() {
        return colorRepository.findAll();
    }
    
    public Color getSingleColor(String id) throws Exception {
        Optional<Color> existingColor=colorRepository.findById(id);
        if(existingColor.isPresent()){
            return existingColor.get();
        }
        throw new Exception("Color does not exist");
    }
    
    public String deleteColor(String id){
        colorRepository.deleteById(id);
        return "Color deleted successfully";
    }

    public Color updateColor(String id, UpdateColorReqDto updateColorReqDto) {
        Optional<Color> existingColor=colorRepository.findById(id);
        if(existingColor.isPresent()){
            existingColor.get().setName(updateColorReqDto.getName());
        }
        return colorRepository.save(existingColor.get());
    }
}
