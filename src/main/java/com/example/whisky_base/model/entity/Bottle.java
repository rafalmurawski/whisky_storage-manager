package com.example.whisky_base.model.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
public class Bottle {
    @Id
    @GeneratedValue
    @Type(type = "uuid-char")
    @Column(name = "uuid", nullable = false, unique = true)
    UUID id;

    @Column(nullable = false, unique = true)
    Double capacity;







}
