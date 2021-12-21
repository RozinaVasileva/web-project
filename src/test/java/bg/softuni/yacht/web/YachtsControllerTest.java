package bg.softuni.yacht.web;

import bg.softuni.yacht.model.entity.enums.YachtTypeEnum;
import bg.softuni.yacht.repository.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
public class YachtsControllerTest {

    private static final String YACHT_CONTROLLER_PREFIX = "/yachts";

    private long testYachtId;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private YachtRepository yachtRepository;
    @Autowired
    private BrandRepository brandRepository;
    @Autowired
    private DestinationRepository destinationRepository;
    @Autowired
    private LogRepository logRepository;

    private YachtTestData testData;

    @BeforeEach
    public void setUp() {


        testData = new YachtTestData(
                userRepository,
                yachtRepository,
                brandRepository,
                destinationRepository,
                 logRepository);

        testData.init();
        testYachtId = testData.getTestYachtId();
    }



    @Test
    @WithMockUser(value = "pesho", roles = {"USER", "ADMIN"})
    void testShouldReturnValidStatusViewNameAndModel() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(
                YACHT_CONTROLLER_PREFIX + "/yacht-details/{id}", testYachtId
        )).
                andExpect(status().isOk()).
                andExpect(view().name("yacht-details")).
                andExpect(model().attributeExists("yacht"));
    }

    @Test
    @WithMockUser(value = "pesho", roles = {"USER", "ADMIN"})
    void addYacht() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(YACHT_CONTROLLER_PREFIX + "/add")
                .param("model", "test yacht").
                        param("type", YachtTypeEnum.LUXURY.name()).
                        param("imageUrl", "https://www.charterworld.com/images/yachts/42m%20Moonen%20motor%20yacht%20Sofia-001.jpg").
                        param("cabins", "3").
                        param("description", "Description test").
                        param("length", "12").
                        param("price", "100").
                        param("year", "2020").
                        param("brand", "BENETTI").
                        param("destination", "Greece").
                        with(csrf())).
                andExpect(status().is3xxRedirection());

        Assertions.assertEquals(9, yachtRepository.count());
    }

    @Test
    @WithMockUser(value = "pesho", roles = {"USER", "ADMIN"})
    public void testIfYachtsReturnCorrectStatusCode() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(YACHT_CONTROLLER_PREFIX + "/all-yachts"))
                .andExpect(status().isOk());
    }

}
