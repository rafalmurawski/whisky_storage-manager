package com.example.whisky_base.repo;

import com.example.whisky_base.model.entity.Bottle;
import com.sun.glass.ui.View;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface BottleRepo extends JpaRepository<Bottle, UUID> {

    Optional<Bottle> findByCapacity(Double capacity);

}
