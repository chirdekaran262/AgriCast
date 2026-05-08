package com.backend.crop_price_backend.dto;

import java.time.LocalDate;

public class DailyPriceDTO {

    private LocalDate date;
    private Double avgPrice;

    public DailyPriceDTO(LocalDate date, Double avgPrice) {
        this.date = date;
        this.avgPrice = avgPrice;
    }

    public LocalDate getDate() {
        return date;
    }

    public Double getAvgPrice() {
        return avgPrice;
    }
}