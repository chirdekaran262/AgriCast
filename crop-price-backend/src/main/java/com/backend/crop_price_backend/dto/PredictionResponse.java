package com.backend.crop_price_backend.dto;

import java.util.List;

public class PredictionResponse {

    private String crop;
    private List<PredictionPoint> predictions;
    public String getCrop() {
        return crop;
    }

    public void setCrop(String crop) {
        this.crop = crop;
    }

	public List<PredictionPoint> getPredictions() {
		return predictions;
	}

	public void setPredictions(List<PredictionPoint> predictions) {
		this.predictions = predictions;
	}

}