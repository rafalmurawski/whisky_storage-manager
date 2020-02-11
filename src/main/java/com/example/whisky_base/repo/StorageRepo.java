package com.example.whisky_base.repo;

import com.example.whisky_base.model.entity.Bottle;
import com.example.whisky_base.model.entity.Storage;
import com.example.whisky_base.model.entity.User;
import com.example.whisky_base.model.entity.Whisky;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface StorageRepo extends JpaRepository<Storage, UUID> {

    List<Storage> findAllByUser(User user);

    Optional<Storage> findByUserAndWhiskyAndBottle(Optional<User> user, Optional<Whisky> whisky, Optional<Bottle> bottle);
}
