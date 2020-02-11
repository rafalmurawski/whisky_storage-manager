package com.example.whisky_base.model.entity;

import com.example.whisky_base.model.RegionOfProduction;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@NoArgsConstructor
@Getter
@Setter
public class Distillery {
    @Id
    @GeneratedValue
    @Type(type = "uuid-char")
    @Column(name = "uuid", nullable = false, unique = true)
    @JsonIgnore
    UUID id;

    @Column(nullable = false, unique = true)
    String name;

    @Enumerated(value = EnumType.STRING)
    RegionOfProduction regionOfProduction;

    public Distillery(String name, RegionOfProduction regionOfProduction) {
        this.name = name;
        this.regionOfProduction = regionOfProduction;
    }


}
