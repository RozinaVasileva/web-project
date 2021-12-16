package bg.softuni.yacht;

import bg.softuni.yacht.service.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class YachtDBApplicationInit implements CommandLineRunner {


    private final UserService userService;
    private final BrandService brandService;
    private final DestinationService destinationService;
    private final YachtService yachtService;
    private final TourService tourService;

    public YachtDBApplicationInit(UserService userService, BrandService brandService, DestinationService destinationService, YachtService yachtService, TourService tourService) {
        this.userService = userService;
        this.brandService = brandService;
        this.destinationService = destinationService;
        this.yachtService = yachtService;
        this.tourService = tourService;
    }

    @Override
    public void run(String... args) throws Exception {
        userService.seedUsers();
        brandService.seedBrands();
        destinationService.seedDestinations();
        yachtService.seedYachts();
        tourService.seedTours();




    }
}
