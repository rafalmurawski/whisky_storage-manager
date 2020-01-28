package com.example.whisky_base.controller;

import com.example.whisky_base.model.RegionOfProduction;
import com.example.whisky_base.model.TypeOfWhisky;
import com.example.whisky_base.model.entity.Distillery;
import com.example.whisky_base.model.entity.Whisky;
import com.example.whisky_base.service.WhiskyService;
import lombok.AccessLevel;
import lombok.Value;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/whisky")
public class WhiskyApi {

    WhiskyService whiskyService;

    @Autowired
    public WhiskyApi(WhiskyService whiskyService) {
        this.whiskyService = whiskyService;
    }

    @GetMapping
    public List<Whisky> showAll() {
        return whiskyService.showAll();
    }

    @PostMapping
    public Whisky addWhisky(@RequestBody WhiskyCommandForPOST whiskyCommandForPOST) {
        return whiskyService.addWhisky(whiskyCommandForPOST);
    }

    @Value
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class WhiskyCommandForPOST{
        String distilleryName;
        String whiskyName;
        String regionOfProduction;
        String typeOfWhisky;


    }

}


