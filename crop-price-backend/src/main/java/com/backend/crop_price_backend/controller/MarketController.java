package com.backend.crop_price_backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.backend.crop_price_backend.repo.PriceRepository;

@RestController
@RequestMapping("/markets")
@CrossOrigin("*")
public class MarketController {

    @Autowired
    private PriceRepository priceRepository;

    @GetMapping("/{cropId}")
    public List<String> getMarkets(
            @PathVariable Long cropId
    ) {

        return priceRepository
                .getMarketsByCrop(cropId);
    }
}