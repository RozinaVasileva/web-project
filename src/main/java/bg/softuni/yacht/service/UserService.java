package bg.softuni.yacht.service;

import bg.softuni.yacht.model.entity.UserEntity;
import bg.softuni.yacht.model.service.UserRegistrationServiceModel;

public interface UserService {

    void seedUsers();
    void registerAndLoginUser(UserRegistrationServiceModel registrationServiceModel);
    boolean usernameExists(String username);

    UserEntity findByName(String username);
}
