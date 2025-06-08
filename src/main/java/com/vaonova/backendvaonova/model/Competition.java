package com.vaonova.backendvaonova.model;

public class Competition {
    private Integer oneStar;
    private Integer twoStar;
    private Integer threeStar;
    private Integer fourStar;
    private Integer fiveStar;


    public Competition(Integer oneStar, Integer twoStar, Integer threeStar, Integer fourStar, Integer fiveStar) {
        this.oneStar = oneStar;
        this.twoStar = twoStar;
        this.threeStar = threeStar;
        this.fourStar = fourStar;
        this.fiveStar = fiveStar;
    }

public Integer getOneStar() {
        return oneStar;
    }

    public void setOneStar(Integer oneStar) {
        this.oneStar = oneStar;
    }

    public Integer getTwoStar() {
        return twoStar;
    }

    public void setTwoStar(Integer twoStar) {
        this.twoStar = twoStar;
    }

    public Integer getThreeStar() {
        return threeStar;
    }

    public void setThreeStar(Integer threeStar) {
        this.threeStar = threeStar;
    }

    public Integer getFourStar() {
        return fourStar;
    }

    public void setFourStar(Integer fourStar) {
        this.fourStar = fourStar;
    }

    public Integer getFiveStar() {
        return fiveStar;
    }

    public void setFiveStar(Integer fiveStar) {
        this.fiveStar = fiveStar;
    }
}
