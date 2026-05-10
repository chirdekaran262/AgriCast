package com.backend.crop_price_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.backend.crop_price_backend.dto.DashboardResponse;
import com.backend.crop_price_backend.service.DashboardService;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    @GetMapping("/{cropId}")
    public DashboardResponse getDashboard(

            @PathVariable Long cropId,

            @RequestParam(required = false)
            String market
    ) {

        return dashboardService
                .getDashboard(cropId, market);
    }
     
}