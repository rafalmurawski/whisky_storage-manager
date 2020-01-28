package com.example.whisky_base.service;

import com.example.whisky_base.controller.WhiskyApi;
import com.example.whisky_base.model.RegionOfProduction;
import com.example.whisky_base.model.TypeOfWhisky;
import com.example.whisky_base.model.entity.Whisky;
import com.example.whisky_base.repo.DistilleryRepo;
import com.example.whisky_base.repo.WhiskyRepo;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class WhiskyService {

    WhiskyRepo whiskyRepo;
    DistilleryRepo distilleryRepo;

    @Autowired
    public WhiskyService(WhiskyRepo whiskyRepo, DistilleryRepo distilleryRepo) {
        this.whiskyRepo = whiskyRepo;
        this.distilleryRepo = distilleryRepo;
    }

   public List<Whisky> showAll(){
        return whiskyRepo.findAll();
   }

    public Whisky addWhisky(WhiskyApi.WhiskyCommandForPOST whiskyCommandForPOST) {
        Whisky whisky = Whisky.builder()
                .name(whiskyCommandForPOST.getWhiskyName().toUpperCase())
                .typeOfWhisky(TypeOfWhisky.valueOf(whiskyCommandForPOST.getTypeOfWhisky()))
                .regionOfProduction(RegionOfProduction.valueOf(whiskyCommandForPOST.getRegionOfProduction()))
                .distillery(distilleryRepo.findByName(whiskyCommandForPOST.getDistilleryName()))
                .build();


        return whiskyRepo.save(whisky);


    }
}
