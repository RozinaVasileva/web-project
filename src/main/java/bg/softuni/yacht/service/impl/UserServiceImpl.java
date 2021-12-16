package bg.softuni.yacht.service.impl;

import bg.softuni.yacht.model.entity.UserEntity;
import bg.softuni.yacht.model.entity.UserRoleEntity;
import bg.softuni.yacht.model.entity.enums.UserRoleEnum;
import bg.softuni.yacht.model.service.UserRegistrationServiceModel;
import bg.softuni.yacht.repository.UserRepository;
import bg.softuni.yacht.repository.UserRoleRepository;
import bg.softuni.yacht.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    //todo
    //private final String DEFAULT_PROFILE_PICTURE = "https://res.cloudinary.com/dmknnra7k/image/upload/v1639476272/245-2454602_tanni-chand-default-user-image-png_amareo.png";

    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;
    private final YachtDBUserService yachtDBUserService;


    public UserServiceImpl(UserRepository userRepository, UserRoleRepository userRoleRepository, PasswordEncoder passwordEncoder, ModelMapper modelMapper, YachtDBUserService yachtDBUserService) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
        this.yachtDBUserService = yachtDBUserService;
    }


    @Override
    public void seedUsers() {

        if (userRepository.count() == 0) {
            UserRoleEntity adminRole = new UserRoleEntity().setRole(UserRoleEnum.ADMIN);
            UserRoleEntity userRole = new UserRoleEntity().setRole(UserRoleEnum.USER);

            userRoleRepository.saveAll(List.of(adminRole, userRole));

            UserEntity admin = new UserEntity().setUsername("admin").setFullName("Admin Adminov").setPassword(passwordEncoder.encode("secret")).setEmail("admin@abv.bg").setPhoneNumber("0898000000");
            UserEntity user = new UserEntity().setUsername("user").setFullName("User Userov").setPassword(passwordEncoder.encode("secret")).setEmail("user@abv.bg").setPhoneNumber("0888000000");
            admin.setRoles(List.of(adminRole, userRole));
            user.setRoles(List.of(userRole));
            userRepository.saveAll(List.of(admin, user));
        }

    }

    @Override
    public void registerAndLoginUser(UserRegistrationServiceModel userRegistrationServiceModel) {

        UserEntity newUser = modelMapper.map(userRegistrationServiceModel, UserEntity.class);

        newUser.setPassword(passwordEncoder.encode(userRegistrationServiceModel.getPassword()));

        UserRoleEntity userRoleEntity = userRoleRepository
                .findByRole(UserRoleEnum.USER)
                .orElseThrow(() -> new IllegalStateException("USER role not found."));

        newUser.addRole(userRoleEntity);
        newUser = userRepository.save(newUser);
        UserDetails principal = yachtDBUserService.loadUserByUsername(newUser.getUsername());

        Authentication authentication = new UsernamePasswordAuthenticationToken(
                principal,
                newUser.getPassword(),
                principal.getAuthorities()

        );


        SecurityContextHolder.getContext().setAuthentication(authentication);

    }

    @Override
    public boolean usernameExists(String username) {
        return userRepository.findByUsername(username).isPresent();

    }

    @Override
    public UserEntity findByName(String username) {
        return userRepository.findByUsername(username).orElseThrow(IllegalArgumentException::new);
    }
}
