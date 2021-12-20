package bg.softuni.yacht.model.binding;


import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;


import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;


public class TourAddBindingModel {

    @Size(min = 2)
    private String name;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Future(message = "Date must be in the future")
    private LocalDate startedDate;
    @Positive
    private int days;
    @DecimalMin("100")
    private BigDecimal price;
    @Size(min = 10)
    private String description;
    @NotNull
    private MultipartFile imageUrl;
    @NotNull
    private String destination;
    @NotNull
    private String yacht;

    public TourAddBindingModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getStartedDate() {
        return startedDate;
    }

    public void setStartedDate(LocalDate startedDate) {
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

    public MultipartFile getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(MultipartFile imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getYacht() {
        return yacht;
    }

    public void setYacht(String yacht) {
        this.yacht = yacht;
    }
}
