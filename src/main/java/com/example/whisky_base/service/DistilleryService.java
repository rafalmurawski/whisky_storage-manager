package com.example.whisky_base.service;

import com.example.whisky_base.controller.DistilleryApi.DistilleryCommand;
import com.example.whisky_base.model.RegionOfProduction;
import com.example.whisky_base.model.entity.Distillery;
import com.example.whisky_base.repo.DistilleryRepo;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DistilleryService {

    DistilleryRepo distilleryRepo;

    @Autowired
    public DistilleryService(DistilleryRepo distilleryRepo) {
        this.distilleryRepo = distilleryRepo;
    }

    public Distillery addDistillery(DistilleryCommand distilleryCommand) {
        Distillery distillery = new Distillery();
        distillery.setName(distilleryCommand.getName().toUpperCase());
        distillery.setRegionOfProduction(RegionOfProduction.valueOf(distilleryCommand.getRegionOfProduction().toUpperCase()));
        distilleryRepo.save(distillery);
        return distillery;

    }

    public List<Distillery> showByRegionOfProduction(String regionOfProduction) {
        return distilleryRepo.findAllByRegionOfProduction(RegionOfProduction.valueOf(regionOfProduction));


    }


    public Boolean deleteDistillery(String name) {
        System.out.println(name);

        Optional<Distillery> byName = distilleryRepo.findByName(name);
        //wywalić getta
        distilleryRepo.delete(byName.get());


        return null;
    }


}