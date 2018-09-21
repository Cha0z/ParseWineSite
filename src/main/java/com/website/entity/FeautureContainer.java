package com.website.entity;

public class FeautureContainer {
    private ProductFeature featureType;
    private String feautureName;


    public FeautureContainer(ProductFeature featureType, String feautureName) {
        this.featureType = featureType;
        this.feautureName = feautureName;
    }

    public ProductFeature getFeatureType() {
        return featureType;
    }

    public String getFeautureName() {
        return feautureName;
    }
}
