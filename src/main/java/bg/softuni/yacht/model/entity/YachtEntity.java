package bg.softuni.yacht.model.entity;

import bg.softuni.yacht.model.entity.enums.YachtTypeEnum;
import com.google.gson.annotations.Expose;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "yachts")
public class YachtEntity extends BaseEntity{


    @Column(nullable = false, unique = true)
    private String model;
    @Enumerated(EnumType.STRING)
    private YachtTypeEnum type;
    @Column(nullable = false)
    private BigDecimal price;
    @Column(nullable = false)
    private double length;
    @Column(nullable = false)
    private int year;
    @Column(nullable = false)
    private int cabins;
    @Column(columnDefinition = "TEXT")
    private String description;
    @Column(nullable = false)
    private String imageUrl;
    @ManyToOne
    private BrandEntity brand;
    @ManyToOne
    private DestinationEntity destination;
    @ManyToOne
    private UserEntity user;


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
