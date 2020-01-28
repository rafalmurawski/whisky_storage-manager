package com.example.whisky_base.controller;

import com.example.whisky_base.model.entity.Distillery;
import com.example.whisky_base.service.DistilleryService;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
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
    public Distillery addDistillery(@RequestBody Distillery distillery) {
       return distilleryService.addDistillery(distillery);
    }

    @GetMapping
    public List<Distillery> showAll() {
        return distilleryService.showAll();
    }

    @DeleteMapping
    public void deleteDistillery(@RequestParam String name) {
        System.out.println(name);
       distilleryService.deleteDistillery(name);
    }
}
