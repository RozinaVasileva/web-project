package bg.softuni.yacht.model.view;

import bg.softuni.yacht.model.entity.BrandEntity;
import bg.softuni.yacht.model.entity.DestinationEntity;
import bg.softuni.yacht.model.entity.UserEntity;
import bg.softuni.yacht.model.entity.enums.YachtTypeEnum;


import java.math.BigDecimal;

public class YachtViewModel {

    private Long id;
    private String model;
    private YachtTypeEnum type;
    private BigDecimal price;
    private double length;
    private int year;
    private int cabins;
    private String description;
    private String imageUrl;
    private BrandEntity brand;
    private DestinationEntity destination;
    private UserEntity user;



    public YachtViewModel() {
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

    public BrandEntity getBrand() {
        return brand;
    }

    public void setBrand(BrandEntity brand) {
        this.brand = brand;
    }

    public DestinationEntity getDestination() {
        return destination;
    }

    public void setDestination(DestinationEntity destination) {
        this.destination = destination;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}
