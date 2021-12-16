package bg.softuni.yacht.model.entity;


import com.google.gson.annotations.Expose;

import javax.persistence.*;

@Entity
@Table(name = "brands")
public class BrandEntity extends BaseEntity{


    @Column(nullable = false)
    private String name;


    //todo brand info


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
