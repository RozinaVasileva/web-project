package bg.softuni.yacht.model.view;

import bg.softuni.yacht.model.entity.enums.YachtTypeEnum;

import java.math.BigDecimal;

public class YachtCardViewModel {

    private Long id;
    private String model;
    private YachtTypeEnum type;
    private BigDecimal price;
    private String imageUrl;
    private String brand;
    private String destination;

    public YachtCardViewModel() {
    }

    public Long getId() {
        return id;
    }

    public YachtCardViewModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getModel() {
        return model;
    }

    public YachtCardViewModel setModel(String model) {
        this.model = model;
        return this;
    }

    public YachtTypeEnum getType() {
        return type;
    }

    public YachtCardViewModel setType(YachtTypeEnum type) {
        this.type = type;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public YachtCardViewModel setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public YachtCardViewModel setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public String getBrand() {
        return brand;
    }

    public YachtCardViewModel setBrand(String brand) {
        this.brand = brand;
        return this;
    }

    public String getDestination() {
        return destination;
    }

    public YachtCardViewModel setDestination(String destination) {
        this.destination = destination;
        return this;
    }
}
