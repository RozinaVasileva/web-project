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
        brandEntity = brandRepository.save(brandEntity);

        DestinationEntity destinationEntity = new DestinationEntity();
        destinationEntity.setName("Greece");
        destinationEntity.setImageUrl("https://www.charterworld.com/images/yachts/42m%20Moonen%20motor%20yacht%20Sofia-001.jpg");
        destinationEntity.setDescription("Some interesting description");
        destinationEntity = destinationRepository.save(destinationEntity);


        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("pesho").setPassword("xyz").setFullName("petar petrov").setEmail("rozina@abv.bg").setPhoneNumber("0888000088");
        userEntity = userRepository.save(userEntity);


        YachtEntity sr41 = new YachtEntity();

        sr41.setModel("SR");
        sr41.setImageUrl("https://www.charterworld.com/images/yachts/42m%20Moonen%20motor%20yacht%20Sofia-001.jpg");
        sr41.setBrand(brandEntity);
        sr41.setDestination(destinationEntity);
        sr41.setCabins(2);
        sr41.setLength(12);
        sr41.setDescription("Some description");
        sr41.setPrice(BigDecimal.TEN);
        sr41.setYear(2020);
        sr41.setType(YachtTypeEnum.LUXURY);
        sr41.setUser(userEntity);

        sr41 = yachtRepository.save(sr41);
        testYachtId = sr41.getId();

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
