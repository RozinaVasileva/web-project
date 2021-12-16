package bg.softuni.yacht.model.view;


import bg.softuni.yacht.model.entity.TourEntity;

import java.util.List;

public class DestinationViewModel {

    private String name;
    private String imageUrl;
    private String description;
    private List<TourEntity> tours;

    public DestinationViewModel(String name, String imageUrl, String description) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.description = description;
    }

    public DestinationViewModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<TourEntity> getTours() {
        return tours;
    }

    public void setTours(List<TourEntity> tours) {
        this.tours = tours;
    }
}
