package com.example.whisky_base.repo;

import com.example.whisky_base.model.entity.Whisky;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface WhiskyRepo extends JpaRepository<Whisky, UUID> {


}
