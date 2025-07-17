package com.vaonova.backendvaonova.dto;

import com.vaonova.backendvaonova.model.BusinessType;

public class RequestIdeaDto {
    private Double longitude;
    private Double latitude;
    private BusinessType businessType;
    private Double budget;
    private String description;

    public RequestIdeaDto(Double longitude, Double latitude, BusinessType businessType, Double budget, String description) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.businessType = businessType;
        this.budget = budget;
        this.description = description;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public BusinessType getBusinessType() {
        return businessType;
    }

    public void setBusinessType(BusinessType businessType) {
        this.businessType = businessType;
    }

    public Double getBudget() {
        return budget;
    }

    public void setBudget(Double budget) {
        this.budget = budget;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
