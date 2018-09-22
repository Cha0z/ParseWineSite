package com.website.entity;

import java.io.Serializable;

public class Product implements Serializable {

    private String title;
    private String type;
    private String region;
    private String brand;
    private String country;
    private String volume;
    private String sweetness;
    private String alcoholPercent;


    public Product(String title, String type, String region,
                   String brand, String country, String volume,
                   String sweetness, String alcoholPercent) {
        this.title = title;
        this.type = type;
        this.region = region;
        this.brand = brand;
        this.country = country;
        this.volume = volume;
        this.sweetness = sweetness;
        this.alcoholPercent = alcoholPercent;
    }




    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getSweetness() {
        return sweetness;
    }

    public void setSweetness(String sweetness) {
        this.sweetness = sweetness;
    }

    public String getAlcoholPercent() {
        return alcoholPercent;
    }

    public void setAlcoholPercent(String alcoholPercent) {
        this.alcoholPercent = alcoholPercent;
    }



    @Override
    public String toString() {
        return "Product{" +
                "title='" + title + '\'' +
                ", type='" + type + '\'' +
                ", region='" + region + '\'' +
                ", brand='" + brand + '\'' +
                ", country='" + country + '\'' +
                ", volume='" + volume + '\'' +
                ", sweetness='" + sweetness + '\'' +
                ", alcoholPercent='" + alcoholPercent + '\'' +
                '}';
    }
}
