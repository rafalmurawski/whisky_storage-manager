package com.example.whisky_base.controller;

import com.example.whisky_base.model.RegionOfProduction;
import com.example.whisky_base.model.entity.Distillery;
import com.example.whisky_base.service.DistilleryService;
import lombok.AccessLevel;
import lombok.Value;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/distillery")
public class DistilleryApi {


    DistilleryService distilleryService;

    @Autowired
    public DistilleryApi(DistilleryService distilleryService) {
        this.distilleryService = distilleryService;
    }


    @PostMapping
    public Distillery addDistillery(@RequestBody DistilleryCommand distilleryCommand) {
       return distilleryService.addDistillery(distilleryCommand);
    }

    @GetMapping
    public List<Distillery> showByRegionOfProduction(@RequestParam String regionOfProduction ) {
        return distilleryService.showByRegionOfProduction(regionOfProduction);
    }

//    @GetMapping
//    public RegionOfProduction[] showAllRegions(){
//        return RegionOfProduction.values();
//    }


    @DeleteMapping
    public Boolean deleteDistillery(@RequestParam String name) {
         return distilleryService.deleteDistillery(name);
    }

    @Value
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class DistilleryCommand {
        String name;
        String regionOfProduction;
    }
}
