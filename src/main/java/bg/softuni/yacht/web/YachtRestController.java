package bg.softuni.yacht.web;


import bg.softuni.yacht.model.view.YachtCardViewModel;
import bg.softuni.yacht.model.view.YachtViewModel;
import bg.softuni.yacht.repository.YachtRepository;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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
    public ResponseEntity<List<YachtCardViewModel>> findAll(){

        List<YachtCardViewModel> yachtCardViewModels = yachtRepository
                .findAll()
                .stream()
                .map(yachtEntity -> {
                    YachtCardViewModel yachtCardViewModel = modelMapper.map(yachtEntity, YachtCardViewModel.class);
                    yachtCardViewModel.setBrand(yachtEntity.getBrand().getName());
                    yachtCardViewModel.setDestination(yachtEntity.getDestination().getName());

                    return yachtCardViewModel;

                        }).collect(Collectors.toList());

        return ResponseEntity
                .ok()
                .body(yachtCardViewModels);

    }


}
