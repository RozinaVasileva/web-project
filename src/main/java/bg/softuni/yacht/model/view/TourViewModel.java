package bg.softuni.yacht.model.view;

import bg.softuni.yacht.model.entity.DestinationEntity;
import bg.softuni.yacht.model.entity.YachtEntity;


import java.math.BigDecimal;


public class TourViewModel {

    private Long id;
    private String name;
    private String startedDate;
    private int days;
    private BigDecimal price;
    private String description;
    private String imageUrl;
    private DestinationEntity destination;
    private YachtEntity yacht;


    public TourViewModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStartedDate() {
        return startedDate;
    }

    public void setStartedDate(String startedDate) {
        this.startedDate = startedDate;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
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

    public DestinationEntity getDestination() {
        return destination;
    }

    public void setDestination(DestinationEntity destination) {
        this.destination = destination;
    }

    public YachtEntity getYacht() {
        return yacht;
    }

    public void setYacht(YachtEntity yacht) {
        this.yacht = yacht;
    }
}
