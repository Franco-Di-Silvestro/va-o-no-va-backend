package com.vaonova.backendvaonova.model;

import java.util.List;

public class AnalizedIdea extends Idea {
    private Risk risk;
    private Competition competition;
    private Integer viabilityScore;
    private List<String> recommendations;

    public AnalizedIdea(Double longitude, Double latitude, BusinessType businessType, Double budget, Risk risk, Competition competition, Integer viabilityScore, List<String> recommendations) {
        super(longitude, latitude, businessType, budget);
        this.risk = risk;
        this.competition = competition;
        this.viabilityScore = viabilityScore;
        this.recommendations = recommendations;
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
