package com.backend.crop_price_backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.crop_price_backend.dto.CropResponse;
import com.backend.crop_price_backend.service.CropService;

@RestController
public class CropController {

    @Autowired
    private CropService cropService;

    @GetMapping("/crops")
    public List<CropResponse> getAllCrops() {
        return cropService.getAllCrops();
    }
}