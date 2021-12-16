package bg.softuni.yacht.repository;

import bg.softuni.yacht.model.entity.DestinationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DestinationRepository extends JpaRepository<DestinationEntity, Long> {

    @Query("SELECT d.name FROM DestinationEntity d" )
    List<String> findAllDestinationNames();

    Optional<DestinationEntity> findByName(String name);
}
