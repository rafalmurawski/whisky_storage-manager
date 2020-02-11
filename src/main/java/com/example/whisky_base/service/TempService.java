package com.example.whisky_base.service;

import com.example.whisky_base.model.RegionOfProduction;
import com.example.whisky_base.model.TypeOfWhisky;
import com.example.whisky_base.model.entity.*;
import com.example.whisky_base.repo.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;


@Service
public class TempService {

    DistilleryRepo distilleryRepo;
    UserRepo userRepo;
    BottleRepo bottleRepo;
    WhiskyRepo whiskyRepo;
    StorageRepo storageRepo;
    UserRoleRepo userRoleRepo;
    PasswordEncoder passwordEncoder;

    public TempService(DistilleryRepo distilleryRepo, UserRepo userRepo, BottleRepo bottleRepo, WhiskyRepo whiskyRepo, StorageRepo storageRepo, UserRoleRepo userRoleRepo, PasswordEncoder passwordEncoder) {
        this.distilleryRepo = distilleryRepo;
        this.userRepo = userRepo;
        this.bottleRepo = bottleRepo;
        this.whiskyRepo = whiskyRepo;
        this.storageRepo = storageRepo;
        this.userRoleRepo = userRoleRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void addStartDistilleries() {

        List<String> islayList = Arrays.asList("Ardbeg", "Bowmore", "Bruichladdich", "Bunnahabhain", "Caol Ila", "Port Askaig", "Kilchoman", "Lagavulin", "Laphroaig", "Port Charlotte ");
        List<String> campbeltownList = Arrays.asList("Glen Scotia", "Springbank");
        List<String> highLandList = Arrays.asList("Aberfeldy", "Auchroisk", "Balblair", "Ben Nevis", "Blair Athol", "Clynelish", "Dalmore", "Dalwhinnie", "Deanston", "Edradour", "Fettercairn", "Glencadam", "Glen Garioch", "Glengoyne", "Glenmorangie", "Glen Ord", "Glenturret", "Loch Lomond", "Lochnagar", "Oban", "Pulteney", "Teaninich", "Tullibardine");
        List<String> lowLandList = Arrays.asList("Auchentoshan", "Bladnoch", "Glenkinchie");
        List<String> speysideList = Arrays.asList("Aberlour", "Allt-á-Bhainne", "Ardmore", "Aultmore", "Balmenach", "Balvenie", "BenRiach", "Benrinnes", "Benromach", "Brackla", "Cardhu", "Cragganmore", "Craigellachie", "Dailuaine", "Drumguish", "Dufftown", "Duncan Taylor", "Glenallachie", "Glenburgie", "Glendronach", "Glendullan", "Glen Elgin", "Glen Grant", "Glenfarclas", "Glenfiddich", "Glen Keith", "The Glenlivet", "Glenlossie", "Glen Moray", "Glenrothes", "Glen Spey", "Glentauchers", "Inchgower", "Kininvie", "Knockando", "Knockdhu", "Linkwood", "Longmorn", "The Macallan", "Macduff", "Mannochmore", "Miltonduff", "Mortlach", "Speyburn", "The Speyside", "Strathisla", "Strathmill", "Tamdhu", "Tomatin", "Tomintoul", "Tormore");
        List<String> otherIslands = Arrays.asList("Abhainn Dearg", "Arran", "Lochranza", "Isle of Jura", "Highland Park", "Scapa", "Talisker", "Tobermory");
        islayList.stream().map(u -> new Distillery(u.toUpperCase(), RegionOfProduction.ISLAY)).forEach(u -> distilleryRepo.save(u));
        campbeltownList.stream().map(u -> new Distillery(u.toUpperCase(), RegionOfProduction.CAMPBELTOWN)).forEach(u -> distilleryRepo.save(u));
        highLandList.stream().map(u -> new Distillery(u.toUpperCase(), RegionOfProduction.HIGHLAND)).forEach(u -> distilleryRepo.save(u));
        lowLandList.stream().map(u -> new Distillery(u.toUpperCase(), RegionOfProduction.LOWLAND)).forEach(u -> distilleryRepo.save(u));
        speysideList.stream().map(u -> new Distillery(u.toUpperCase(), RegionOfProduction.SPEYSIDE)).forEach(u -> distilleryRepo.save(u));
        otherIslands.stream().map(u -> new Distillery(u.toUpperCase(), RegionOfProduction.OTHER_ISLANDS)).forEach(u -> distilleryRepo.save(u));


        User marcin = new User();
        marcin.setEnable(true);
        marcin.setUser_mail("marcin@gmail.com");
        marcin.setUser_pass(passwordEncoder.encode("marcinpass"));

        User rafal = new User();
        rafal.setEnable(true);
        rafal.setUser_mail("rafal@gmail.com");
        rafal.setUser_pass(passwordEncoder.encode("rafalpass"));

        userRepo.save(marcin);
        userRepo.save(rafal);

        UserRole marcinRole = new UserRole();
        marcinRole.setUser_role("ROLE_USER");
        marcinRole.setUser(marcin);

        userRoleRepo.save(marcinRole);

        UserRole rafalRole = new UserRole();
        rafalRole.setUser_role("ROLE_USER");
        rafalRole.setUser(rafal);

        userRoleRepo.save(rafalRole);


        Bottle bottle1 = new Bottle();
        bottle1.setCapacity(0.5);
        Bottle bottle2 = new Bottle();
        bottle2.setCapacity(0.7);
        Bottle bottle3 = new Bottle();
        bottle3.setCapacity(1.0);

        List<Bottle> bottleList = Arrays.asList(bottle1, bottle2, bottle3);
        bottleRepo.saveAll(bottleList);

        Whisky laphroaig10 = new Whisky();
        laphroaig10.setDistillery(distilleryRepo.findByName("laphroaig".toUpperCase()).get());
        laphroaig10.setName("10");
        laphroaig10.setTypeOfWhisky(TypeOfWhisky.valueOf("single_malt".toUpperCase()));

        Whisky ardbegTen = new Whisky();
        ardbegTen.setDistillery(distilleryRepo.findByName("ardbeg".toUpperCase()).get());
        ardbegTen.setName("TEN");
        ardbegTen.setTypeOfWhisky(TypeOfWhisky.valueOf("single_malt".toUpperCase()));

        Whisky lagavulin16 = new Whisky();
        lagavulin16.setDistillery(distilleryRepo.findByName("lagavulin".toUpperCase()).get());
        lagavulin16.setName("12");
        lagavulin16.setTypeOfWhisky(TypeOfWhisky.valueOf("single_malt".toUpperCase()));

        whiskyRepo.save(laphroaig10);
        whiskyRepo.save(ardbegTen);
        whiskyRepo.save(lagavulin16);

        Storage marcinstorage1 = new Storage();
        Storage marcinstorage2 = new Storage();
        Storage rafalstorage1 = new Storage();
        Storage rafalstorage2 = new Storage();
        Storage rafalstorage3 = new Storage();


        marcinstorage1.setBottle(bottle2);
//        marcinstorage1.getWhiskyList().add(laphroaig10);
        marcinstorage1.setWhisky(laphroaig10);
        marcinstorage1.setQuantityOfBottles(1);
        marcinstorage1.setUser(marcin);

        storageRepo.save(marcinstorage1);

        marcinstorage2.setBottle(bottle2);
//        marcinstorage2.getWhiskyList().add(ardbegTen);
        marcinstorage2.setWhisky(ardbegTen);
        marcinstorage2.setQuantityOfBottles(2);
        marcinstorage2.setUser(marcin);

        storageRepo.save(marcinstorage2);


        rafalstorage1.setBottle(bottle2);
//        rafalstorage1.getWhiskyList().add(ardbegTen);
        rafalstorage1.setWhisky(ardbegTen);
        rafalstorage1.setQuantityOfBottles(1);
        rafalstorage1.setUser(rafal);

        storageRepo.save(rafalstorage1);

        rafalstorage2.setBottle(bottle2);
//        rafalstorage2.getWhiskyList().add(laphroaig10);
        rafalstorage2.setWhisky(laphroaig10);
        rafalstorage2.setQuantityOfBottles(2);
        rafalstorage2.setUser(rafal);


        storageRepo.save(rafalstorage2);

        rafalstorage3.setBottle(bottle2);
//        rafalstorage3.getWhiskyList().add(lagavulin16);
        rafalstorage3.setWhisky(lagavulin16);
        rafalstorage3.setQuantityOfBottles(1);
        rafalstorage3.setUser(rafal);


        storageRepo.save(rafalstorage3);


    }
}
