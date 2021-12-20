package bg.softuni.yacht.model.service;

import com.google.gson.annotations.Expose;



public class DestinationServiceModel {


    private Long id;
    @Expose
    private String name;
    @Expose
    private String imageUrl;
    @Expose
    private String description;

    public DestinationServiceModel() {
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
}
