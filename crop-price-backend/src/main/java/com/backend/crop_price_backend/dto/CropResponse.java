package com.backend.crop_price_backend.dto;

public class CropResponse {

    private Long id;
    private String name;

    public CropResponse() {
    }

    public CropResponse(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}