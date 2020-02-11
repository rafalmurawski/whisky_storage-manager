package com.example.whisky_base.controller;

import com.example.whisky_base.model.entity.Whisky;
import com.example.whisky_base.service.WhiskyService;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Value;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static lombok.AccessLevel.PRIVATE;

@RestController
@RequestMapping("/whisky")
@CrossOrigin(value = "http://localhost:4200/whisky")
public class WhiskyApi {

    WhiskyService whiskyService;

    @Autowired
    public WhiskyApi(WhiskyService whiskyService) {
        this.whiskyService = whiskyService;
    }

    @GetMapping
    public List<WhiskyView> showAll() {
        return whiskyService.showAll().stream().map(WhiskyView::fromEntity).collect(toList());
    }


    @PostMapping
    public Whisky addWhisky(@RequestBody WhiskyCommandForPOST whiskyCommandForPOST) {
        return whiskyService.addWhisky(whiskyCommandForPOST);
    }


    @Value
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class WhiskyCommandForPOST {
        String distilleryName;
        String whiskyName;
        String typeOfWhisky;


    }

    @Value
    @Builder
    @FieldDefaults(level = PRIVATE)
    private static class WhiskyView {
        String distilleryName;
        String whiskyName;
        String typeOfWhisky;
        String regionOfProduction;


        static WhiskyView fromEntity(Whisky whisky) {
            return WhiskyView.builder()
                    .whiskyName(whisky.getName())
                    .distilleryName(whisky.getDistillery().getName())
                    .typeOfWhisky(whisky.getTypeOfWhisky().toString())
                    .regionOfProduction(whisky.getDistillery().getRegionOfProduction().toString())
                    .build();
        }


    }
}


