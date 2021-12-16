package bg.softuni.yacht.web;

import bg.softuni.yacht.model.binding.TourAddBindingModel;
import bg.softuni.yacht.model.service.TourServiceModel;
import bg.softuni.yacht.model.view.TourViewModel;
import bg.softuni.yacht.service.*;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/tours")
public class TourController {
    private final ModelMapper modelMapper;
    private final YachtService yachtService;
    private final TourService tourService;
    private final DestinationService destinationService;
    private final UserService userService;
    private final CloudinaryService cloudinaryService;

    public TourController(ModelMapper modelMapper, YachtService yachtService, TourService tourService, DestinationService destinationService, UserService userService, CloudinaryService cloudinaryService) {
        this.modelMapper = modelMapper;
        this.yachtService = yachtService;
        this.tourService = tourService;
        this.destinationService = destinationService;
        this.userService = userService;
        this.cloudinaryService = cloudinaryService;
    }


    @GetMapping("/all-tours")
    public String getAllTours(Model model){



        model.addAttribute("tours", tourService.findAllTours());
        return "all-tours";
    }


    @ModelAttribute("tourAddBindingModel")
    public TourAddBindingModel createBindingModel() {
        return new TourAddBindingModel();
    }

    @GetMapping("/add")
    public String add(Model model, @AuthenticationPrincipal UserDetails principal) {

            String username = principal.getUsername();

        model.addAttribute("yachts", yachtService.findAllYachtModelsByUser(username));
        model.addAttribute("destinations", destinationService.findAllDestinationNames());


        return "add-tour";
    }

    @PostMapping("/add")
    public String addTour(@Valid TourAddBindingModel tourAddBindingModel,
                          BindingResult bindingResult,
                          RedirectAttributes redirectAttributes,
                          @AuthenticationPrincipal UserDetails principal) throws IOException {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("tourAddBindingModel", tourAddBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.tourAddBindingModel", bindingResult);


            return "redirect:add";
        }

        TourServiceModel tourServiceModel = modelMapper
                .map(tourAddBindingModel, TourServiceModel.class);

        tourServiceModel.setUsername(principal.getUsername());
        tourServiceModel.setImageUrl(cloudinaryService.uploadImage(tourAddBindingModel.getImageUrl()));
        tourService.createTour(tourServiceModel);
        return "redirect:/home";
    }

    @GetMapping("/tour-details/{id}")
    public String tourDetails(@PathVariable Long id, Model model){

        TourViewModel tourViewModel = tourService.findById(id);

        model.addAttribute("tour", tourViewModel);
        model.addAttribute("three", tourService.findThreeTours());

        return "tour-details";

    }


}
