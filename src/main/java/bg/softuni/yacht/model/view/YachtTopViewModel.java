package bg.softuni.yacht.model.view;



public class YachtTopViewModel {

    private Long id;
    private String model;
    private String imageUrl;
    private String destination;

    public YachtTopViewModel() {
    }

    public Long getId() {
        return id;
    }

    public YachtTopViewModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getModel() {
        return model;
    }

    public YachtTopViewModel setModel(String model) {
        this.model = model;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public YachtTopViewModel setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public String getDestination() {
        return destination;
    }

    public YachtTopViewModel setDestination(String destination) {
        this.destination = destination;
        return this;
    }
}
