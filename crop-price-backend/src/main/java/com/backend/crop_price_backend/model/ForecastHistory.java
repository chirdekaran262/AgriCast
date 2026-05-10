package com.backend.crop_price_backend.model;

import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
public class ForecastHistory {

    @Id
    @GeneratedValue
    private Long id;

    private String cropName;

    private String market;

    private LocalDate predictionDate;

    private Double predictedPrice;

    private Double actualPrice;

    private Double errorPercent;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCropName() {
		return cropName;
	}

	public void setCropName(String cropName) {
		this.cropName = cropName;
	}

	public String getMarket() {
		return market;
	}

	public void setMarket(String market) {
		this.market = market;
	}

	public LocalDate getPredictionDate() {
		return predictionDate;
	}

	public void setPredictionDate(LocalDate predictionDate) {
		this.predictionDate = predictionDate;
	}

	public Double getPredictedPrice() {
		return predictedPrice;
	}

	public void setPredictedPrice(Double predictedPrice) {
		this.predictedPrice = predictedPrice;
	}

	public Double getActualPrice() {
		return actualPrice;
	}

	public void setActualPrice(Double actualPrice) {
		this.actualPrice = actualPrice;
	}

	public Double getErrorPercent() {
		return errorPercent;
	}

	public void setErrorPercent(Double errorPercent) {
		this.errorPercent = errorPercent;
	}


    
}