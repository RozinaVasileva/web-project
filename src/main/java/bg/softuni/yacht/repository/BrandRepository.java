package bg.softuni.yacht.repository;

import bg.softuni.yacht.model.entity.BrandEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BrandRepository extends JpaRepository<BrandEntity, Long> {
    @Query("SELECT b.name FROM BrandEntity b ORDER BY b.name")
    List<String> findAllBrandNames();

    Optional<BrandEntity> findByName(String name);
}
