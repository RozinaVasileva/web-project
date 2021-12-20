package bg.softuni.yacht.service;

import bg.softuni.yacht.model.entity.TourEntity;
import bg.softuni.yacht.model.service.TourServiceModel;
import bg.softuni.yacht.model.view.TourViewModel;

import java.util.List;
import java.util.Optional;

public interface TourService {

    void seedTours();

    void createTour(TourServiceModel tourServiceModel);


    TourEntity findById(Long id);

    List<TourServiceModel> findAllTours();

    TourEntity findEntityById(Long tourId);

    List<TourServiceModel> findThreeBestPricesTours();
}
