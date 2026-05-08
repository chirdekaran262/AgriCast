package com.backend.crop_price_backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.backend.crop_price_backend.dto.PredictionResponse;
import com.backend.crop_price_backend.model.Crop;
import com.backend.crop_price_backend.repo.CropRepository;

@Service
public class PythonPredictionService
        implements PredictionService {

    @Autowired
    private CropRepository cropRepository;

    private final RestTemplate restTemplate =
            new RestTemplate();

    @Override
    public PredictionResponse predict(Long cropId) {

        Crop crop = cropRepository.findById(cropId)
                .orElseThrow();

        String url =
                "http://localhost:8000/predict/"
                + crop.getName();

        PredictionResponse response =
                restTemplate.getForObject(
                        url,
                        PredictionResponse.class
                );

        return response;
    }
}