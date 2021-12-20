package bg.softuni.yacht.model.entity;


import com.google.gson.annotations.Expose;

import javax.persistence.*;

@Entity
@Table(name = "brands")
public class BrandEntity extends BaseEntity{


    @Column(nullable = false)
    private String name;
    @Column(columnDefinition = "TEXT")
    private String description;



    public BrandEntity() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
