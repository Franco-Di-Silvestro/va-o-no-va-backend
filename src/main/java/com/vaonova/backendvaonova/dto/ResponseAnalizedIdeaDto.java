package com.vaonova.backendvaonova.dto;

import com.vaonova.backendvaonova.model.BusinessType;
import com.vaonova.backendvaonova.model.Competition;
import com.vaonova.backendvaonova.model.Risk;

import java.util.List;

public class ResponseAnalizedIdeaDto {
    private Double longitude;
    private Double latitude;
    private BusinessType businessType;
    private Double budget;
    private Risk risk;
    private Competition competition;
    private Integer viabilityScore;
    private List<String> recommendations;

    public ResponseAnalizedIdeaDto(Double longitude, Double latitude, BusinessType businessType, Double budget, Risk risk, Competition competition, Integer viabilityScore, List<String> recommendations) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.businessType = businessType;
        this.budget = budget;
        this.risk = risk;
        this.competition = competition;
        this.viabilityScore = viabilityScore;
        this.recommendations = recommendations;
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

    public Risk getRisk() {
        return risk;
    }

    public void setRisk(Risk risk) {
        this.risk = risk;
    }

    public Competition getCompetition() {
        return competition;
    }

    public void setCompetition(Competition competition) {
        this.competition = competition;
    }

    public Integer getViabilityScore() {
        return viabilityScore;
    }

    public void setViabilityScore(Integer viabilityScore) {
        this.viabilityScore = viabilityScore;
    }

    public List<String> getRecommendations() {
        return recommendations;
    }

    public void setRecommendations(List<String> recommendations) {
        this.recommendations = recommendations;
    }
}
