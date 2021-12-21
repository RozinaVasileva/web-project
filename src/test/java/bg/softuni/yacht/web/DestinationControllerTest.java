package bg.softuni.yacht.web;

import bg.softuni.yacht.model.entity.DestinationEntity;
import bg.softuni.yacht.model.entity.UserEntity;
import bg.softuni.yacht.repository.DestinationRepository;
import bg.softuni.yacht.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
public class DestinationControllerTest {
    private static final String DESTINATION_CONTROLLER_PREFIX = "/destinations";

    private long testDestinationId;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DestinationRepository destinationRepository;
    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public  void setUp(){
        init();
    }

    @Test
    @WithMockUser(value = "pesho", roles = {"USER", "ADMIN"})
    void testShouldReturnValidStatusViewNameAndModel() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(
                DESTINATION_CONTROLLER_PREFIX + "/destination-details/{id}", testDestinationId
        )).
                andExpect(status().isOk()).
                andExpect(view().name("destination-details")).
                andExpect(model().attributeExists("destination"));
    }

    @Test
    @WithMockUser(value = "pesho", roles = {"USER", "ADMIN"})
    public void testIfToursReturnCorrectStatusCode() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(DESTINATION_CONTROLLER_PREFIX + "/all-destinations"))
                .andExpect(status().isOk());
    }

    private void init(){
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("pesho").setPassword("xyz").setFullName("petar petrov").setEmail("rozina@abv.bg").setPhoneNumber("0888000088");
        userEntity = userRepository.save(userEntity);

        DestinationEntity destinationEntity = new DestinationEntity();
        destinationEntity.setDescription("Something very interesting");
        destinationEntity.setName("Greece");
        destinationEntity.setImageUrl("https://res.cloudinary.com/dmknnra7k/image/upload/v1638470309/u58sea8sarfuz49pupnc.jpg");

        destinationRepository.save(destinationEntity);
        testDestinationId = destinationEntity.getId();
    }
}
