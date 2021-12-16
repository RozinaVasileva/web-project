package bg.softuni.yacht.model.service;


import bg.softuni.yacht.model.entity.enums.YachtTypeEnum;
import com.google.gson.annotations.Expose;

import java.math.BigDecimal;

public class YachtServiceModel {

    private Long id;
    @Expose
    private String model;
    @Expose
    private YachtTypeEnum type;
    @Expose
    private BigDecimal price;
    @Expose
    private double length;
    @Expose
    private int year;
    @Expose
    private int cabins;
    @Expose
    private String description;
    @Expose
    private String imageUrl;
    @Expose
    private String brand;
    @Expose
    private String destination;
    @Expose
    private String username;


    public YachtServiceModel() {

    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public YachtTypeEnum getType() {
        return type;
    }

    public void setType(YachtTypeEnum type) {
        this.type = type;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }


    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getCabins() {
        return cabins;
    }

    public void setCabins(int cabins) {
        this.cabins = cabins;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
