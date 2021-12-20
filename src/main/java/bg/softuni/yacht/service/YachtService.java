package bg.softuni.yacht.service;

import bg.softuni.yacht.model.entity.BrandEntity;
import bg.softuni.yacht.model.entity.YachtEntity;
import bg.softuni.yacht.model.service.YachtServiceModel;
import bg.softuni.yacht.model.view.YachtViewModel;

import java.util.List;

public interface YachtService {

    void seedYachts();

    void createYacht(YachtServiceModel yachtServiceModel);

    YachtEntity findById(Long id);


    YachtEntity findByName(String yacht);

    List<String> findAllYachtModels();

    List<YachtServiceModel> findAllYachts();

    List<YachtServiceModel> findLastAddedThreeYachts();

    List<YachtServiceModel> findFourYachtByPrice();


    List<String> findAllYachtModelsByUser(String username);

    void deleteById(Long id);
}
