package com.backend.crop_price_backend.service;

import com.backend.crop_price_backend.dto.PredictionResponse;


public interface PredictionService {

	PredictionResponse predict(Long cropId);
}