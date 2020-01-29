package com.example.whisky_base.model.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@NoArgsConstructor
@Getter
@Setter
public class Distillery {
    @Id
    @GeneratedValue
    @Type(type="uuid-char")
    @Column(name = "uuid", nullable = false, unique = true)
    UUID id;

    @Column(nullable = false)
    String name;

}
