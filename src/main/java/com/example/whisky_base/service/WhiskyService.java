package com.example.whisky_base.service;

import com.example.whisky_base.controller.WhiskyApi;
import com.example.whisky_base.model.TypeOfWhisky;
import com.example.whisky_base.model.entity.Distillery;
import com.example.whisky_base.model.entity.Whisky;
import com.example.whisky_base.repo.DistilleryRepo;
import com.example.whisky_base.repo.WhiskyRepo;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public List<Whisky> showAll() {
        return whiskyRepo.findAll();
    }


    public Whisky addWhisky(WhiskyApi.WhiskyCommandForPOST whiskyCommandForPOST) {
        Optional<Distillery> distillery = distilleryRepo.findByName(whiskyCommandForPOST.getDistilleryName().toUpperCase());

        Whisky whisky = Whisky.builder()
                .name(whiskyCommandForPOST.getWhiskyName().toUpperCase())
                .typeOfWhisky(TypeOfWhisky.valueOf(whiskyCommandForPOST.getTypeOfWhisky().toUpperCase()))
                .distillery(distillery.orElseThrow(() -> new UsernameNotFoundException("wsisky not found")))
                .build();


        return whiskyRepo.save(whisky);


    }
}
