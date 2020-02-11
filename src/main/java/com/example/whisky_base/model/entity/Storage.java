package com.example.whisky_base.model.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"whisky_uuid", "user_user_mail", "bottle_uuid" })})
public class Storage {

    @Id
    @GeneratedValue
    @Type(type = "uuid-char")
    @Column(name = "uuid", nullable = false, unique = true)
    UUID id;

    @ManyToOne
    User user;

    @ManyToOne
    Whisky whisky;


    @ManyToOne
    Bottle bottle;

    Integer quantityOfBottles;


}
