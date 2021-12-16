package bg.softuni.yacht.web;

import bg.softuni.yacht.model.view.DestinationViewModel;
import bg.softuni.yacht.repository.DestinationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/destinations")
@RestController
public class DestinationRestController {

    private final DestinationRepository destinationRepository;
    private final ModelMapper modelMapper;

    public DestinationRestController(DestinationRepository destinationRepository, ModelMapper modelMapper) {
        this.destinationRepository = destinationRepository;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/api")
    public List<DestinationViewModel> findAll(){


       return destinationRepository
                .findAll()
                .stream()
                .map(destinationEntity -> modelMapper.map(destinationEntity, DestinationViewModel.class))
               .collect(Collectors.toList());


    }
}
