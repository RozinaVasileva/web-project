package bg.softuni.yacht.repository;

import bg.softuni.yacht.model.entity.BrandEntity;
import bg.softuni.yacht.model.entity.TourEntity;
import bg.softuni.yacht.model.entity.YachtEntity;
import bg.softuni.yacht.model.service.YachtServiceModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface YachtRepository extends JpaRepository<YachtEntity, Long> {

    Optional<YachtEntity> findByModel(String model);


    @Query("SELECT y.model FROM YachtEntity y")
    List<String> findAllYachtModels();

    //@Query("SELECT y.id FROM YachtEntity y ORDER BY y.price")
    List<YachtEntity>findByOrderByPrice();

    List<YachtEntity>findByOrderByIdDesc();

    List<YachtEntity>findAllByUser_Username(String username);




}
