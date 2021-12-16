package bg.softuni.yacht.web;

import bg.softuni.yacht.model.binding.YachtAddBindingModel;
import bg.softuni.yacht.model.entity.DestinationEntity;
import bg.softuni.yacht.model.entity.YachtEntity;
import bg.softuni.yacht.model.service.YachtServiceModel;
import bg.softuni.yacht.model.view.DestinationViewModel;
import bg.softuni.yacht.model.view.YachtViewModel;
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


@Controller
@RequestMapping("/yachts")
public class YachtsController {

    private final ModelMapper modelMapper;
    private final YachtService yachtService;
    private final BrandService brandService;
    private final DestinationService destinationService;
    private final CloudinaryService  cloudinaryService;

    public YachtsController(ModelMapper modelMapper, YachtService yachtService, BrandService brandService, DestinationService destinationService, CloudinaryService cloudinaryService) {
        this.modelMapper = modelMapper;
        this.yachtService = yachtService;
        this.brandService = brandService;
        this.destinationService = destinationService;
        this.cloudinaryService = cloudinaryService;
    }



    @GetMapping("/all-yachts")
    public String getAllYachts(Model model){
        model.addAttribute("yachts", this.yachtService.findAllYachts());
        return "all-yachts";
    }



    @ModelAttribute("yachtAddBindingModel")
    public YachtAddBindingModel createBindingModel(){
        return new YachtAddBindingModel();
    }


    @GetMapping("/add")
    public String add(Model model){
        model.addAttribute("brands", brandService.findAllBrands());
        model.addAttribute("destinations", destinationService.findAllDestinationNames());

        return "add-yacht";
    }

    @PostMapping("/add")
    public String addYacht(@Valid YachtAddBindingModel yachtAddBindingModel,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes,
                           @AuthenticationPrincipal UserDetails principal) throws IOException {

        if (bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("yachtAddBindingModel", yachtAddBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.yachtAddBindingModel", bindingResult);


            return "redirect:add";
        }

        YachtServiceModel yachtServiceModel = modelMapper
                .map(yachtAddBindingModel, YachtServiceModel.class);

        yachtServiceModel.setUsername(principal.getUsername());
        yachtServiceModel.setImageUrl(cloudinaryService.uploadImage(yachtAddBindingModel.getImageUrl()));
        yachtService.createYacht(yachtServiceModel);
        return "redirect:/home";
    }

    @GetMapping("/yacht-details/{id}")
    public String yachtDetails(@PathVariable Long id, Model model){

       YachtEntity yachtEntity = yachtService.findById(id);

       YachtViewModel yachtViewModel = modelMapper.map(yachtEntity, YachtViewModel.class);
       yachtViewModel.setBrand(yachtEntity.getBrand().getName());

       model.addAttribute("yacht", yachtViewModel);
       model.addAttribute("three", yachtService.findLastAddedThreeYachts());

       return "yacht-details";

    }




}
