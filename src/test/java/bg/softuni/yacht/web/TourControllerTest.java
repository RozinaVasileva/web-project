package bg.softuni.yacht.web;

import bg.softuni.yacht.model.entity.*;
import bg.softuni.yacht.model.entity.enums.YachtTypeEnum;
import bg.softuni.yacht.repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
public class TourControllerTest {

    private static final String TOURS_CONTROLLER_PREFIX = "/tours";

    private long testTourId;
    private long testYachtId;

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TourRepository tourRepository;
    @Autowired
    private DestinationRepository destinationRepository;
    @Autowired
    private BrandRepository brandRepository;
    @Autowired
    private YachtRepository yachtRepository;

    @BeforeEach
    public  void setUp(){
        init();
    }



    @Test
    @WithMockUser(value = "pesho", roles = {"USER", "ADMIN"})
    void testShouldReturnValidStatusViewNameAndModel() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(
                TOURS_CONTROLLER_PREFIX + "/tour-details/{id}", testTourId
        )).
                andExpect(status().isOk()).
                andExpect(view().name("tour-details")).
                andExpect(model().attributeExists("tour"));
    }

    @Test
    @WithMockUser(value = "pesho", roles = {"USER", "ADMIN"})
    public void testIfToursReturnCorrectStatusCode() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(TOURS_CONTROLLER_PREFIX + "/all-tours"))
                .andExpect(status().isOk());
    }





    private void init(){
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

        TourEntity tour = new TourEntity();
        tour.setUser(userEntity);
        tour.setDestination(destinationEntity);
        tour.setStartedDate(LocalDate.of(2002, 5, 20));
        tour.setYacht(tonny);
        tour.setDays(5);
        tour.setPrice(BigDecimal.TEN);
        tour.setName("Discover Greece");
        tour.setDescription("Something very interesting");
        tour.setImageUrl("https://res.cloudinary.com/dmknnra7k/image/upload/v1638470309/u58sea8sarfuz49pupnc.jpg");

        tourRepository.save(tour);
        testTourId = tour.getId();

    }
}
