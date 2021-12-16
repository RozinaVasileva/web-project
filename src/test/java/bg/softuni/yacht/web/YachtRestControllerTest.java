package bg.softuni.yacht.web;


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

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
public class YachtRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BrandRepository brandRepository;
    @Autowired
    private DestinationRepository destinationRepository;
    @Autowired
    private YachtRepository yachtRepository;
    @Autowired
    private LogRepository logRepository;

    private YachtTestData testData;

    @BeforeEach
    public void setUp() {
        testData = new YachtTestData(
                userRepository,
                yachtRepository,
                logRepository,
                brandRepository,
                destinationRepository);
        testData.init();
    }
    //@AfterEach
    //public void tearDown() {
    //    yachtTestData.cleanUp();
    //}

    @Test
    @WithMockUser(value = "pesho", roles = {"USER", "ADMIN"})
    public void testFetchYachts() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/yachts/api"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("[0].model").value("Oasis 40M BO006"))
                .andExpect(jsonPath("[0].type").value(YachtTypeEnum.LUXURY.name()))
                .andExpect(jsonPath("[0].year").value("2020"))
                .andExpect(jsonPath("[1].model").value("Gran Turismo 45"))
                .andExpect(jsonPath("[1].type").value(YachtTypeEnum.MOTOR.name()))
                .andExpect(jsonPath("[1].year").value("2020"));

    }

}
