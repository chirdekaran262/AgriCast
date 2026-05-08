package com.backend.crop_price_backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.backend.crop_price_backend.dto.DailyPriceDTO;

@Service
public class RecommendationService {

    public String getRecommendation(
            List<DailyPriceDTO> prices
    ) {

        if (prices.size() < 6) {
            return "WAIT";
        }

        int size = prices.size();

        double recentAvg =
                prices.subList(size - 3, size)
                        .stream()
                        .mapToDouble(DailyPriceDTO::getAvgPrice)
                        .average()
                        .orElse(0);

        double oldAvg =
                prices.subList(size - 6, size - 3)
                        .stream()
                        .mapToDouble(DailyPriceDTO::getAvgPrice)
                        .average()
                        .orElse(0);

        double changePercent =
                ((recentAvg - oldAvg) / oldAvg) * 100;

        // trend logic
        if (changePercent > 5) {
            return "HOLD";
        }

        if (changePercent < -5) {
            return "SELL";
        }

        return "WAIT";
    }
}