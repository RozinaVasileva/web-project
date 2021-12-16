package bg.softuni.yacht.service.impl;

import bg.softuni.yacht.model.entity.BrandEntity;
import bg.softuni.yacht.model.service.BrandServiceModel;
import bg.softuni.yacht.repository.BrandRepository;
import bg.softuni.yacht.service.BrandService;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

@Service
public class BrandServiceImpl implements BrandService {


    private final Resource brandsFile;
    private final Gson gson;
    private final ModelMapper modelMapper;
    private final BrandRepository brandRepository;

    public BrandServiceImpl(
            @Value("classpath:init/brands.json") Resource brandsFile,
            Gson gson, ModelMapper modelMapper, BrandRepository brandRepository
    ) {
        this.brandsFile = brandsFile;
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.brandRepository = brandRepository;
    }

    @Override
    public void seedBrands() {
        if (brandRepository.count()==0){
            try {
                BrandServiceModel[] brandServiceModels = gson.fromJson(Files.readString(Path.of(brandsFile.getURI())), BrandServiceModel[].class);
                Arrays
                        .stream(brandServiceModels)
                        .forEach(brandServiceModel -> {
                            BrandEntity brandEntity = modelMapper.map(brandServiceModel, BrandEntity.class);
                            brandRepository.save(brandEntity);
                        });
            } catch (IOException e) {
               throw new IllegalStateException("Cannot seed brands");
            }
        }

    }

    @Override
    public List<String> findAllBrands() {
        return brandRepository.findAllBrandNames();
    }

    @Override
    public BrandEntity findByName(String brand) {
        return brandRepository
                .findByName(brand)
                .orElseThrow(IllegalArgumentException::new);
    }
}
