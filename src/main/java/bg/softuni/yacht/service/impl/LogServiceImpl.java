package bg.softuni.yacht.service.impl;

import bg.softuni.yacht.model.entity.LogEntity;
import bg.softuni.yacht.model.entity.TourEntity;
import bg.softuni.yacht.model.entity.UserEntity;
import bg.softuni.yacht.model.service.LogServiceModel;
import bg.softuni.yacht.repository.LogRepository;
import bg.softuni.yacht.service.LogService;
import bg.softuni.yacht.service.TourService;
import bg.softuni.yacht.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LogServiceImpl implements LogService {

    private final LogRepository logRepository;
    private final UserService userService;
    private final TourService tourService;
    private final ModelMapper modelMapper;

    public LogServiceImpl(LogRepository logRepository, UserService userService, TourService tourService, ModelMapper modelMapper) {
        this.logRepository = logRepository;
        this.userService = userService;
        this.tourService = tourService;
        this.modelMapper = modelMapper;
    }

    @Override
    public void createLog(String action, Long tourId) {

        TourEntity tourEntity = tourService
                .findEntityById(tourId);

        Authentication authentication = SecurityContextHolder
                .getContext()
                .getAuthentication();

        String username = authentication.getName();

        UserEntity userEntity = userService.findByName(username);

        LogEntity logEntity = new LogEntity();
        logEntity.setTourEntity(tourEntity);
        logEntity.setUserEntity(userEntity);
        logEntity.setAction(action);
        logEntity.setDateTime(LocalDateTime.now());

        logRepository.save(logEntity);

    }

    @Override
    public List<LogServiceModel> findAllLogs() {
        return logRepository.findAll().stream().map(logEntity -> {
            LogServiceModel logServiceModel = modelMapper.map(logEntity, LogServiceModel.class);
            logServiceModel.setTour(logEntity.getTourEntity().getName());
            logServiceModel.setUser(logEntity.getUserEntity().getUsername());

            return logServiceModel;
        }).collect(Collectors.toList());
    }
}
