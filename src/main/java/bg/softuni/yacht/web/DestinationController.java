package bg.softuni.yacht.web;

import bg.softuni.yacht.model.entity.DestinationEntity;
import bg.softuni.yacht.model.entity.TourEntity;
import bg.softuni.yacht.model.view.DestinationViewModel;
import bg.softuni.yacht.model.view.TourTopViewModel;
import bg.softuni.yacht.service.DestinationService;
import bg.softuni.yacht.service.TourService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/destinations")
public class DestinationController {

    private final DestinationService destinationService;
    private final TourService tourService;
    private final ModelMapper modelMapper;

    public DestinationController(DestinationService destinationService, TourService tourService, ModelMapper modelMapper) {
        this.destinationService = destinationService;
        this.tourService = tourService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/all-destinations")
    public String getAllDestinations(Model model){

        List<DestinationViewModel> destinationViewModels = destinationService
                .findAllDestinations()
                .stream()
                .map(destinationServiceModel -> {
                    DestinationViewModel destinationViewModel = modelMapper.map(destinationServiceModel, DestinationViewModel.class);
                    return destinationViewModel;
                }).collect(Collectors.toList());

        model.addAttribute("destinations", destinationViewModels);
        return "all-destinations";
    }

    @GetMapping("/destination-details/{id}")
    public String destinationsDetails(@PathVariable Long id, Model model) {

        DestinationEntity destinationEntity = destinationService.findById(id);
        DestinationViewModel destinationViewModel = modelMapper.map(destinationEntity, DestinationViewModel.class);
        List<TourTopViewModel> tourTopViewModels = tourService.findAllToursByDestinationId(id).stream().map(tourServiceModel -> {
            TourTopViewModel tourTopViewModel = modelMapper.map(tourServiceModel, TourTopViewModel.class);
            return tourTopViewModel;
        }).collect(Collectors.toList());

        model.addAttribute("destination", destinationViewModel);
        model.addAttribute("tours", tourTopViewModels);


        return "destination-details";
    }
}
