package com.vaonova.backendvaonova.model;

public class Idea {
    private Double longitude;
    private Double latitude;
    private BusinessType businessType;
    private Double budget;

    public Idea(Double longitude, Double latitude, BusinessType businessType, Double budget) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.businessType = businessType;
        this.budget = budget;
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
}
