package com.example.whisky_base.service;

import com.example.whisky_base.model.entity.Distillery;
import com.example.whisky_base.repo.DistilleryRepo;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DistilleryService {

    DistilleryRepo distilleryRepo;

    @Autowired
    public DistilleryService(DistilleryRepo distilleryRepo) {
        this.distilleryRepo = distilleryRepo;
    }

    public Distillery addDistillery(Distillery distillery) {
        distillery.setName(distillery.getName().toUpperCase());
        distilleryRepo.save(distillery);
        return distillery;

    }

    public List<Distillery> showAll() {
        return distilleryRepo.findAll();
    }

    public void deleteDistillery(String name) {
        System.out.println(name);

        Distillery byName = distilleryRepo.findByName(name);
        distilleryRepo.delete(byName);


    }
}
