package bg.softuni.yacht.model.view;



import java.math.BigDecimal;

public class TourTopViewModel {

    private Long id;
    private String name;
    private String imageUrl;
    private String destination;
    private BigDecimal price;

    public TourTopViewModel() {
    }

    public Long getId() {
        return id;
    }

    public TourTopViewModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public TourTopViewModel setName(String name) {
        this.name = name;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public TourTopViewModel setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public String getDestination() {
        return destination;
    }

    public TourTopViewModel setDestination(String destination) {
        this.destination = destination;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public TourTopViewModel setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }
}
