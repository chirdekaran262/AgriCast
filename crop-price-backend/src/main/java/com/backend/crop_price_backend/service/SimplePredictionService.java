package com.backend.crop_price_backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.crop_price_backend.dto.DailyPriceDTO;
import com.backend.crop_price_backend.repo.PriceRepository;


public class SimplePredictionService {

    @Autowired
    private PriceRepository priceRepository;

    public Double predictNextPrice(Long cropId) {

        List<DailyPriceDTO> prices =
                priceRepository.getDailyAveragePrices(cropId);
        System.out.println("Prices "+prices);
        if (prices.size() < 3) {
            return 0.0;
        }

        int size = prices.size();

        double avg = prices.subList(size - 3, size)
                        .stream()
                        .mapToDouble(DailyPriceDTO::getAvgPrice)
                        .average()
                        .orElse(0);
        System.out.println("Predicted price "+avg);
        return avg*100/100;
    }
}