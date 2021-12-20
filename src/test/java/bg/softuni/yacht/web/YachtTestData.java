package bg.softuni.yacht.web;

import bg.softuni.yacht.model.entity.BrandEntity;
import bg.softuni.yacht.model.entity.DestinationEntity;
import bg.softuni.yacht.model.entity.UserEntity;
import bg.softuni.yacht.model.entity.YachtEntity;
import bg.softuni.yacht.model.entity.enums.YachtTypeEnum;
import bg.softuni.yacht.repository.*;

import java.math.BigDecimal;

public class YachtTestData {

    private long testYachtId;

    private UserRepository userRepository;
    private YachtRepository yachtRepository;
    private LogRepository logRepository;
    private BrandRepository brandRepository;
    private DestinationRepository destinationRepository;

    public YachtTestData(UserRepository userRepository, YachtRepository yachtRepository, LogRepository logRepository, BrandRepository brandRepository, DestinationRepository destinationRepository) {
        this.userRepository = userRepository;
        this.yachtRepository = yachtRepository;
        this.logRepository = logRepository;
        this.brandRepository = brandRepository;
        this.destinationRepository = destinationRepository;
    }


    public void init(){
        BrandEntity brandEntity = new BrandEntity();
        brandEntity.setName("BENETTI");
        brandEntity.setDescription("Some interesting description");
        brandEntity = brandRepository.save(brandEntity);

        DestinationEntity destinationEntity = new DestinationEntity();
        destinationEntity.setName("Greece");
        destinationEntity.setImageUrl("https://www.charterworld.com/images/yachts/42m%20Moonen%20motor%20yacht%20Sofia-001.jpg");
        destinationEntity.setDescription("Some interesting description");
        destinationEntity = destinationRepository.save(destinationEntity);


        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("pesho").setPassword("xyz").setFullName("petar petrov").setEmail("rozina@abv.bg").setPhoneNumber("0888000088");
        userEntity = userRepository.save(userEntity);


        YachtEntity tonny = new YachtEntity();

        tonny.setModel("TONNY");
        tonny.setImageUrl("https://www.charterworld.com/images/yachts/42m%20Moonen%20motor%20yacht%20Sofia-001.jpg");
        tonny.setBrand(brandEntity);
        tonny.setDestination(destinationEntity);
        tonny.setCabins(2);
        tonny.setLength(12);
        tonny.setDescription("Some description");
        tonny.setPrice(BigDecimal.TEN);
        tonny.setYear(2020);
        tonny.setType(YachtTypeEnum.LUXURY);
        tonny.setUser(userEntity);

        tonny = yachtRepository.save(tonny);
        testYachtId = tonny.getId();

        YachtEntity anna = new YachtEntity();

        anna.setModel("ANNA");
        anna.setImageUrl("https://www.charterworld.com/images/yachts/42m%20Moonen%20motor%20yacht%20Sofia-001.jpg");
        anna.setBrand(brandEntity);
        anna.setDestination(destinationEntity);
        anna.setCabins(5);
        anna.setLength(12);
        anna.setDescription("Some description");
        anna.setPrice(BigDecimal.TEN);
        anna.setYear(2021);
        anna.setType(YachtTypeEnum.MOTOR);
        anna.setUser(userEntity);

        anna = yachtRepository.save(anna);

    }
    void cleanUp(){
        logRepository.deleteAll();
        yachtRepository.deleteAll();
        brandRepository.deleteAll();
        destinationRepository.deleteAll();
        userRepository.deleteAll();
    }

    public long getTestYachtId(){
        return testYachtId;
    }


}
