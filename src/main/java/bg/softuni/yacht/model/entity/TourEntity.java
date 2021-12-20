package bg.softuni.yacht.model.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "tours")
public class TourEntity extends BaseEntity{

    @Column(nullable = false, unique = true)
    private String name;
    @Column(nullable = false)
    private LocalDate startedDate;
    @Column(nullable = false)
    private int days;
    @Column(nullable = false)
    private BigDecimal price;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;
    @Column(nullable = false)
    private String imageUrl;
    @ManyToOne
    private DestinationEntity destination;
    @OneToOne
    private YachtEntity yacht;
    @ManyToOne
    private UserEntity user;


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

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}
