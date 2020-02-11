package com.example.whisky_base.service;

import com.example.whisky_base.model.entity.*;
import com.example.whisky_base.repo.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import static com.example.whisky_base.controller.StorageApi.StorageCommand;

@Service
public class StorageService {

    StorageRepo storageRepo;
    UserRepo userRepo;
    WhiskyRepo whiskyRepo;
    BottleRepo bottleRepo;
    DistilleryRepo distilleryRepo;


    public StorageService(StorageRepo storageRepo, UserRepo userRepo, WhiskyRepo whiskyRepo, BottleRepo bottleRepo, DistilleryRepo distilleryRepo) {
        this.storageRepo = storageRepo;
        this.userRepo = userRepo;
        this.whiskyRepo = whiskyRepo;
        this.bottleRepo = bottleRepo;
        this.distilleryRepo = distilleryRepo;
    }


    @Transactional
    public Storage addPosition(StorageCommand storageCommand) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();

        Optional<User> user = userRepo.findById(name);
        Optional<Distillery> distillery = distilleryRepo.findByName(storageCommand.getDistilleryName().toUpperCase());
        Optional<Whisky> whisky = whiskyRepo.findByNameAndDistillery(storageCommand.getWhiskyName().toUpperCase(),
                distillery.orElseThrow(() -> new UsernameNotFoundException("Whisky not found")));
        Optional<Bottle> bottle = bottleRepo.findByCapacity(storageCommand.getBottleCapacity());

        Optional<Storage> whiskyInStorage = storageRepo.findByUserAndWhiskyAndBottle(user, whisky, bottle);


        if (!whiskyInStorage.isPresent()) {


            Storage storage = new Storage();
            storage.setUser(user.orElseThrow(() -> new UsernameNotFoundException("User not found")));
            storage.setWhisky(whisky.orElseThrow(() -> new UsernameNotFoundException("Whisky not found")));
            storage.setBottle(bottle.orElseThrow(() -> new UsernameNotFoundException("Bottle not found")));
            storage.setQuantityOfBottles(storageCommand.getQuantityOfBottles());

            return storageRepo.save(storage);
        } else {

            whiskyInStorage.get().setQuantityOfBottles(whiskyInStorage.get().getQuantityOfBottles() + storageCommand.getQuantityOfBottles());
            return storageRepo.save(whiskyInStorage.get());

        }

    }

    public List<Storage> showMyStorage() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();

        Optional<User> user = userRepo.findById(name);


        return storageRepo.findAllByUser(user.orElseThrow(() -> new UsernameNotFoundException("User not found")));


    }
}
