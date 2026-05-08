package com.backend.crop_price_backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.crop_price_backend.dto.DailyPriceDTO;
import com.backend.crop_price_backend.dto.DashboardResponse;
import com.backend.crop_price_backend.dto.PredictionResponse;
import com.backend.crop_price_backend.dto.PricePoint;
import com.backend.crop_price_backend.model.Crop;
import com.backend.crop_price_backend.repo.CropRepository;
import com.backend.crop_price_backend.repo.PriceRepository;

@Service
public class DashboardService {

    @Autowired
    private PriceRepository priceRepository;

    @Autowired
    private CropRepository cropRepository; 
    
    @Autowired
    private RecommendationService recommendationService;

    @Autowired
    private PredictionService predictionService;
    
    public DashboardResponse getDashboard(Long cropId) {

    	List<DailyPriceDTO> prices =
    	        priceRepository.getDailyAveragePrices(cropId);
        // IMPORTANT FIX
        if (prices.isEmpty()) {
            throw new RuntimeException(
                    "No price data found for cropId: " + cropId
            );
        }
         

        DashboardResponse response = new DashboardResponse();
        Crop crop = cropRepository.findById(cropId)
                .orElseThrow();
        response.setCropName(crop.getName());

        response.setCurrentPrice(
        	    Math.round(
        	        prices.get(prices.size() - 1).getAvgPrice() * 100.0
        	    ) / 100.0
        	);

        response.setRecommendation(
                recommendationService.getRecommendation(prices)
        );

        List<PricePoint> history = prices.stream()
                .map(p -> new PricePoint(
                        p.getDate(),
                        Math.round(p.getAvgPrice() * 100.0) / 100.0
                ))
                .toList();

        response.setHistory(history);
        
        PredictionResponse predictionResponse =
                predictionService.predict(cropId);

        response.setPredictions(
                predictionResponse.getPredictions()
        );     
        response.setPredictedPrice(predictionResponse.getPredictions().get(0).getPrice());
        
        response.setPredictedDate(
        		predictionResponse.getPredictions().get(0).getDate()
        	);
        
        return response;
    }
}