package com.example.whisky_base.repo;

import com.example.whisky_base.model.RegionOfProduction;
import com.example.whisky_base.model.entity.Distillery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface DistilleryRepo extends JpaRepository<Distillery, UUID> {

    Optional<Distillery> findByName(String name);

    List<Distillery> findAllByRegionOfProduction(RegionOfProduction regionOfProduction);
}
