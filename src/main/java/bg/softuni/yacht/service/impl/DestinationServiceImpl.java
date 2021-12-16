package bg.softuni.yacht.service.impl;


import bg.softuni.yacht.model.entity.DestinationEntity;
import bg.softuni.yacht.model.entity.TourEntity;
import bg.softuni.yacht.model.service.DestinationServiceModel;
import bg.softuni.yacht.model.view.DestinationViewModel;
import bg.softuni.yacht.repository.DestinationRepository;
import bg.softuni.yacht.service.DestinationService;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class DestinationServiceImpl implements DestinationService {


    private final Resource destinationsFile;
    private final Gson gson;
    private final DestinationRepository destinationRepository;
    private final ModelMapper modelMapper;

    public DestinationServiceImpl(
            @Value("classpath:init/destinations.json") Resource destinationsFile,
            Gson gson, DestinationRepository destinationRepository, ModelMapper modelMapper) {
        this.destinationsFile = destinationsFile;
        this.gson = gson;
        this.destinationRepository = destinationRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void seedDestinations() {

        if (destinationRepository.count() == 0) {

            try {
                DestinationServiceModel[] destinationServiceModels = gson.fromJson(Files.readString(Path.of(destinationsFile.getURI())), DestinationServiceModel[].class);
                Arrays.stream(destinationServiceModels).forEach(d->{
                    DestinationEntity destinationEntity = modelMapper.map(d, DestinationEntity.class);
                    destinationRepository.save(destinationEntity);
                });
            } catch (IOException e) {
                throw new IllegalStateException("Cannot seed destinations");
            }

        }


    }

    @Override
    public List<String> findAllDestinationNames() {
        return destinationRepository.findAllDestinationNames();
    }

    @Override
    public DestinationEntity findByName(String destination) {
        return destinationRepository.findByName(destination).orElseThrow(IllegalArgumentException::new);
    }




}
