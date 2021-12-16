package bg.softuni.yacht.service.impl;

import bg.softuni.yacht.model.entity.DestinationEntity;
import bg.softuni.yacht.model.entity.TourEntity;
import bg.softuni.yacht.model.entity.UserEntity;
import bg.softuni.yacht.model.entity.YachtEntity;
import bg.softuni.yacht.model.service.TourServiceModel;
import bg.softuni.yacht.model.view.TourViewModel;
import bg.softuni.yacht.repository.TourRepository;
import bg.softuni.yacht.repository.UserRepository;
import bg.softuni.yacht.service.DestinationService;
import bg.softuni.yacht.service.TourService;
import bg.softuni.yacht.service.UserService;
import bg.softuni.yacht.service.YachtService;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TourServiceImpl implements TourService {


    private final Resource toursFile;
    private final Gson gson;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final YachtService yachtService;
    private final DestinationService destinationService;
    private final TourRepository tourRepository;
    private final UserService userService;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy");

    public TourServiceImpl(@Value("classpath:init/tours.json") Resource toursFile, Gson gson, ModelMapper modelMapper, UserRepository userRepository, YachtService yachtService, DestinationService destinationService, TourRepository tourRepository, UserService userService) {
        this.toursFile = toursFile;
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.yachtService = yachtService;
        this.destinationService = destinationService;
        this.tourRepository = tourRepository;
        this.userService = userService;
    }


    @Override
    public void seedTours() {

        if (tourRepository.count()==0){
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                TourServiceModel[] tourServiceModels = gson.fromJson(Files.readString(Path.of(toursFile.getURI())), TourServiceModel[].class);
                Arrays.stream(tourServiceModels).forEach(tourServiceModel -> {
                    TourEntity tourEntity = modelMapper.map(tourServiceModel, TourEntity.class);
                    tourEntity.setYacht(yachtService.findByName(tourServiceModel.getYacht()));
                    tourEntity.setDestination(destinationService.findByName(tourServiceModel.getDestination()));
                    tourEntity.setStartedDate(LocalDate.parse(tourServiceModel.getStartedDate(), formatter));
                    tourEntity.setUser(userService.findByName(tourServiceModel.getUsername()));
                    tourRepository.save(tourEntity);
                });
            } catch (IOException e) {
                throw new IllegalStateException("Cannot seed tours");
            }

        }


    }

    @Override
    public void createTour(TourServiceModel tourServiceModel) {

        TourEntity tourEntity = modelMapper.map(tourServiceModel, TourEntity.class);
        UserEntity creator = userRepository
                .findByUsername(tourServiceModel.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("Creator " + tourServiceModel.getUsername() + " could not be found"));
        tourEntity.setUser(creator);
        YachtEntity yachtEntity = yachtService.findByName(tourServiceModel.getYacht());
        DestinationEntity destinationEntity = destinationService.findByName(tourServiceModel.getDestination());

        tourEntity.setYacht(yachtEntity);
        tourEntity.setDestination(destinationEntity);
        tourRepository.save(tourEntity);
    }

    @Override
    public TourViewModel findById(Long id) {


        TourEntity tourEntity = tourRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        TourViewModel tourViewModel = modelMapper.map(tourEntity, TourViewModel.class);
        tourViewModel.setStartedDate(tourEntity.getStartedDate().format(formatter));
        return tourViewModel;
    }

    @Override
    public List<TourViewModel> findAllTours() {
        return tourRepository.findAll().stream().map(t -> {

            return modelMapper.map(t, TourViewModel.class);
        }).collect(Collectors.toList());
    }

    @Override
    public TourEntity findEntityById(Long tourId) {
        return tourRepository
                .findById(tourId)
                .orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public List<TourViewModel> findThreeTours() {
        return tourRepository.findByOrderByPriceDesc().stream().limit(3).map(tourEntity -> {
            TourViewModel tvm = modelMapper.map(tourEntity, TourViewModel.class);
            return tvm;
        }).collect(Collectors.toList());


    }
}
