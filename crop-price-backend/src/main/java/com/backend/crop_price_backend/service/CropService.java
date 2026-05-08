package com.backend.crop_price_backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.crop_price_backend.dto.CropResponse;
import com.backend.crop_price_backend.model.Crop;
import com.backend.crop_price_backend.repo.CropRepository;

@Service
public class CropService {

    @Autowired
    private CropRepository cropRepository;

    public List<CropResponse> getAllCrops() {

        List<Crop> crops = cropRepository.findAll();

        return crops.stream()
                .map(crop -> new CropResponse(
                        crop.getId(),
                        crop.getName()
                ))
                .toList();
    }
}