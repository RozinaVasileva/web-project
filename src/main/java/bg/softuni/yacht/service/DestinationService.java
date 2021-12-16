package bg.softuni.yacht.service;

import bg.softuni.yacht.model.entity.DestinationEntity;
import bg.softuni.yacht.model.service.YachtServiceModel;
import bg.softuni.yacht.model.view.DestinationViewModel;

import java.util.List;

public interface DestinationService {

    void seedDestinations();

    List<String> findAllDestinationNames();

    DestinationEntity findByName(String destination);




}
