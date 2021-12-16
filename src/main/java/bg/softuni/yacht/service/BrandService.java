package bg.softuni.yacht.service;

import bg.softuni.yacht.model.entity.BrandEntity;

import java.util.List;

public interface BrandService {

    void seedBrands();

    List<String> findAllBrands();

    BrandEntity findByName(String brand);
}
