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

import com.ecommerce.api.dto.request.CreateColorReqDto;
import com.ecommerce.api.dto.request.UpdateColorReqDto;
import com.ecommerce.api.model.Color;
import com.ecommerce.api.service.ColorService;

import lombok.RequiredArgsConstructor;




@RestController
@RequestMapping("/api/v1/colors")
@RequiredArgsConstructor
public class ColorController {

    final ColorService colorService;
    
    @PostMapping("")
    public Color createColor(@RequestBody CreateColorReqDto createColorReqDto) throws Exception{
        return colorService.createColor(createColorReqDto);

    }

    @GetMapping("")
    public List<Color> getAllColors() {
        return colorService.getAllColors();
    }
    
    @GetMapping("/{id}")
    public Color getSingleColor(@PathVariable String id) throws Exception {
        return colorService.getSingleColor(id);
    }
    
    @DeleteMapping("/{id}")
    public String deleteColor(@PathVariable String id){
        return colorService.deleteColor(id);
    }

    @PutMapping("/{id}")
    public Color updateColor(@PathVariable String id, UpdateColorReqDto updateColorReqDto) {
        return colorService.updateColor(id, updateColorReqDto);
    }
}
