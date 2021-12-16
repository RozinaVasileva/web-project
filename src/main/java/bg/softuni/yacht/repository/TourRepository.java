package bg.softuni.yacht.repository;

import bg.softuni.yacht.model.entity.TourEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TourRepository extends JpaRepository<TourEntity, Long> {

    List<TourEntity>findByOrderByPriceDesc();
}
