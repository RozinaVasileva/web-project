package bg.softuni.yacht.service;

import bg.softuni.yacht.model.entity.DestinationEntity;
import bg.softuni.yacht.model.service.DestinationServiceModel;
import bg.softuni.yacht.model.service.YachtServiceModel;
import bg.softuni.yacht.model.view.DestinationViewModel;

import java.util.Collection;
import java.util.List;

public interface DestinationService {

    void seedDestinations();

    List<String> findAllDestinationNames();

    DestinationEntity findByName(String destination);


    DestinationEntity findById(Long id);

    List<DestinationServiceModel> findAllDestinations();
}
