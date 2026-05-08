package com.backend.crop_price_backend.dto;

import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class DashboardResponse {

    private String cropName;
    private Double currentPrice;
    private String recommendation;
    private List<PricePoint> history;
    private Double predictedPrice;
    private String predictedDate;
    private List<PredictionPoint> predictions;
    // GETTERS & SETTERS

    public String getCropName() {
        return cropName;
    }

    public void setCropName(String cropName) {
        this.cropName = cropName;
    }

    public Double getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(Double currentPrice) {
        this.currentPrice = currentPrice;
    }

    public String getRecommendation() {
        return recommendation;
    }

    public void setRecommendation(String recommendation) {
        this.recommendation = recommendation;
    }

    public List<PricePoint> getHistory() {
        return history;
    }

    public void setHistory(List<PricePoint> history) {
        this.history = history;
    }

	public Double getPredictedPrice() {
		return predictedPrice;
	}

	public void setPredictedPrice(Double predictedPrice) {
		this.predictedPrice = predictedPrice;
	}

	public String getPredictedDate() {
		return predictedDate;
	}

	public void setPredictedDate(String predictedDate) {
		this.predictedDate = predictedDate;
	}

	public List<PredictionPoint> getPredictions() {
		return predictions;
	}

	public void setPredictions(List<PredictionPoint> predictions) {
		this.predictions = predictions;
	}
    
    
}