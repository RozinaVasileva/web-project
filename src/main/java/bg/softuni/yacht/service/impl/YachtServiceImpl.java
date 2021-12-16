package bg.softuni.yacht.service.impl;

import bg.softuni.yacht.model.entity.BrandEntity;
import bg.softuni.yacht.model.entity.DestinationEntity;
import bg.softuni.yacht.model.entity.UserEntity;
import bg.softuni.yacht.model.entity.YachtEntity;
import bg.softuni.yacht.model.service.YachtServiceModel;
import bg.softuni.yacht.model.view.YachtViewModel;
import bg.softuni.yacht.repository.UserRepository;
import bg.softuni.yacht.repository.YachtRepository;
import bg.softuni.yacht.service.BrandService;
import bg.softuni.yacht.service.DestinationService;
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
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class YachtServiceImpl implements YachtService {
    private final Resource yachtsFile;
    private final Gson gson;
    private final YachtRepository yachtRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final BrandService brandService;
    private final DestinationService destinationService;
    private final UserService userService;



    public YachtServiceImpl(@Value("classpath:init/yachts.json") Resource yachtsFile, Gson gson, YachtRepository yachtRepository, ModelMapper modelMapper, UserRepository userRepository, BrandService brandService, DestinationService destinationService, UserService userService) {
        this.yachtsFile = yachtsFile;
        this.gson = gson;
        this.yachtRepository = yachtRepository;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.brandService = brandService;
        this.destinationService = destinationService;


        this.userService = userService;
    }


    @Override
    public void seedYachts() {

        if (yachtRepository.count()== 0){

            try {

                YachtServiceModel[] yachtServiceModels = gson.fromJson(Files.readString(Path.of(yachtsFile.getURI())), YachtServiceModel[].class);
                Arrays.stream(yachtServiceModels).forEach(y->{
                    YachtEntity yachtEntity = modelMapper.map(y, YachtEntity.class);
                    yachtEntity.setBrand(brandService.findByName(y.getBrand()));
                    yachtEntity.setDestination(destinationService.findByName(y.getDestination()));
                    yachtEntity.setUser(userService.findByName(y.getUsername()));
                    yachtRepository.save(yachtEntity);
                });
            } catch (IOException e) {
                throw new IllegalStateException("Cannot seed yachts");
            }


        }
    }

    @Override
    public void createYacht(YachtServiceModel yachtServiceModel) {

        YachtEntity yachtEntity = modelMapper.map(yachtServiceModel, YachtEntity.class);
        UserEntity creator = userRepository
                .findByUsername(yachtServiceModel.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("Creator " + yachtServiceModel.getUsername() + " could not be found"));
        yachtEntity.setUser(creator);

        BrandEntity brandEntity = brandService
                .findByName(yachtServiceModel.getBrand());
        DestinationEntity destinationEntity = destinationService.findByName(yachtServiceModel.getDestination());

        yachtEntity.setBrand(brandEntity);
        yachtEntity.setDestination(destinationEntity);


        yachtRepository.save(yachtEntity);

    }

    @Override
    public YachtEntity findById(Long id) {
        return yachtRepository
                .findById(id).orElseThrow(IllegalArgumentException::new);


    }

    @Override
    public YachtEntity findByName(String yacht) {
        return yachtRepository.findByModel(yacht).orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public List<String> findAllYachtModels() {

        return yachtRepository.findAllYachtModels();
    }

    @Override
    public List<YachtViewModel> findAllYachts() {
        return yachtRepository.findAll().stream().map(y->{
            YachtViewModel yvm =modelMapper.map(y, YachtViewModel.class);
            yvm.setBrand(y.getBrand().getName());
            //yvm.setDestination(y.getDestination().getName());
            return yvm;
        }).collect(Collectors.toList());
    }

    @Override
    public List<YachtViewModel> findLastAddedThreeYachts() {

        return yachtRepository.findByOrderByIdDesc().stream().limit(3).map(yachtEntity -> {
            YachtViewModel yvm = modelMapper.map(yachtEntity, YachtViewModel.class);
            yvm.setBrand(yachtEntity.getBrand().getName());
            return yvm;
        }).collect(Collectors.toList());

    }

    @Override
    public List<YachtServiceModel> findFourYachtByPrice() {
        return yachtRepository.findByOrderByPrice().stream().map(yachtEntity -> {
            YachtServiceModel ysm = modelMapper.map(yachtEntity, YachtServiceModel.class);
            ysm.setBrand(yachtEntity.getBrand().getName());
            ysm.setDestination(yachtEntity.getDestination().getName());
            return ysm;
        }).limit(4).collect(Collectors.toList());
    }



    @Override
    public List<String> findAllYachtModelsByUser(String username) {
        //return yachtRepository.findAllYachtModelsByUsername(username);
        return yachtRepository.findAllByUser_Username(username)
                .stream()
                .map(yachtEntity -> yachtEntity.getModel())
                .collect(Collectors.toList());
    }


}
