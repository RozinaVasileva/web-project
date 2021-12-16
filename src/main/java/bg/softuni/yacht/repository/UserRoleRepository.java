package bg.softuni.yacht.repository;

import bg.softuni.yacht.model.entity.UserRoleEntity;
import bg.softuni.yacht.model.entity.enums.UserRoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRoleEntity, Long> {

    Optional<UserRoleEntity>findByRole(UserRoleEnum role);
}
