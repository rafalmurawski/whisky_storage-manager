package com.example.whisky_base.controller;

import com.example.whisky_base.model.entity.Storage;
import com.example.whisky_base.service.StorageService;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Value;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static lombok.AccessLevel.PRIVATE;

@RestController
@RequestMapping("/storage")
public class StorageApi {

    StorageService storageService;

    public StorageApi(StorageService storageService) {
        this.storageService = storageService;
    }

    @GetMapping
    public List<StorageView> showMyStorage() {
        return storageService.showMyStorage().stream().map(StorageView::fromEntity).collect(toList());
    }


    @PostMapping
    public Storage addPosition(@RequestBody StorageCommand storageCommand) {
        return storageService.addPosition(storageCommand);

    }

    @Value
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class StorageCommand {
        String whiskyName;
        String distilleryName;
        Integer quantityOfBottles;
        Double bottleCapacity;

    }


    @Value
    @Builder
    @FieldDefaults(level = PRIVATE)
    private static class StorageView {
        String distilleryName;
        String whiskyName;
        String typeOfWhisky;
        String regionOfProduction;
        Double bottleCapacity;
        Integer quantityOfBottles;

        static StorageView fromEntity(Storage storage) {
            return StorageView.builder()
                    .whiskyName(storage.getWhisky().getName())
                    .distilleryName(storage.getWhisky().getDistillery().getName())
                    .typeOfWhisky(storage.getWhisky().getTypeOfWhisky().toString())
                    .regionOfProduction(storage.getWhisky().getDistillery().getRegionOfProduction().toString())

                    .quantityOfBottles(storage.getQuantityOfBottles())
                    .bottleCapacity(storage.getBottle().getCapacity())
                    .build();
        }


    }

}
