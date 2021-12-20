package bg.softuni.yacht.model.binding;

import bg.softuni.yacht.model.entity.enums.YachtTypeEnum;
import org.springframework.web.multipart.MultipartFile;


import javax.validation.constraints.*;
import java.math.BigDecimal;

public class YachtAddBindingModel {

    @Size(min = 2, max = 20)
    private String model;
    @NotNull
    private YachtTypeEnum type;
    @DecimalMin("100")
    private BigDecimal price;
    @Min(value = 1)
    private double length;
    @Min(2000)
    private int year;
    @PositiveOrZero
    private int cabins;
    @Size(min = 10)
    private String description;
    @NotNull
    private MultipartFile imageUrl;
    @NotNull
    private String brand;
    @NotNull
    private String destination;

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

    public MultipartFile getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(MultipartFile imageUrl) {
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
}
