package bg.softuni.yacht.web;

import bg.softuni.yacht.model.view.YachtViewModel;
import bg.softuni.yacht.service.CarouselService;
import bg.softuni.yacht.service.YachtService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class HomeController {

    private final CarouselService carouselService;
    private final YachtService yachtService;
    private final ModelMapper modelMapper;

    public HomeController(CarouselService carouselService, YachtService yachtService, ModelMapper modelMapper) {
        this.carouselService = carouselService;
        this.yachtService = yachtService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("firstImg", carouselService.firstImage());
        model.addAttribute("secondImg", carouselService.secondImage());
        model.addAttribute("thirdImg", carouselService.thirdImage());
        return "index";

    }

    @GetMapping("/home")
    public String home(Model model){
        model.addAttribute("firstImg", carouselService.firstImage());
        model.addAttribute("secondImg", carouselService.secondImage());
        model.addAttribute("thirdImg", carouselService.thirdImage());

        List<YachtViewModel> yachtViewModels = yachtService
                .findFourYachtByPrice()
                .stream()
                .map(yachtServiceModel -> {
                    YachtViewModel yvm = modelMapper.map(yachtServiceModel, YachtViewModel.class);
                    return yvm;
                }).collect(Collectors.toList());


        model.addAttribute("four", yachtViewModels);
        return "home";

    }
}
