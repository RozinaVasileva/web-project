package bg.softuni.yacht.service;

import bg.softuni.yacht.model.entity.TourEntity;
import bg.softuni.yacht.model.service.TourServiceModel;
import bg.softuni.yacht.model.view.TourViewModel;

import java.util.List;
import java.util.Optional;

public interface TourService {

    void seedTours();

    void createTour(TourServiceModel tourServiceModel);


    TourViewModel findById(Long id);

    List<TourViewModel> findAllTours();

    TourEntity findEntityById(Long tourId);

    List<TourViewModel> findThreeTours();
}
