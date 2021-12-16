package bg.softuni.yacht.web;


import bg.softuni.yacht.model.view.YachtViewModel;
import bg.softuni.yacht.repository.YachtRepository;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/yachts")
@RestController
public class YachtRestController {

    private final YachtRepository yachtRepository;
    private final ModelMapper modelMapper;

    public YachtRestController(YachtRepository yachtRepository, ModelMapper modelMapper) {
        this.yachtRepository = yachtRepository;
        this.modelMapper = modelMapper;
    }


    @GetMapping("/api")
    public ResponseEntity<List<YachtViewModel>> findAll(){

        List<YachtViewModel> yachtViewModels = yachtRepository
                .findAll()
                .stream()
                .map(yachtEntity -> {
                    YachtViewModel yachtViewModel = modelMapper.map(yachtEntity, YachtViewModel.class);
                    yachtViewModel.setBrand(yachtEntity.getBrand().getName());

                    return yachtViewModel;

                        }).collect(Collectors.toList());

        return ResponseEntity
                .ok()
                .body(yachtViewModels);

    }
}
