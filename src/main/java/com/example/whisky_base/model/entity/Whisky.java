package com.example.whisky_base.model.entity;

import com.example.whisky_base.model.TypeOfWhisky;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Builder
public class Whisky {
    @Id
    @Column(name = "uuid", nullable = false, unique = true)
    @GeneratedValue
    @Type(type = "uuid-char")
    UUID id;

    @Column(nullable = false)
    String name;

    @Enumerated(value = EnumType.STRING)
    TypeOfWhisky typeOfWhisky;

    @ManyToOne
    Distillery distillery;


}
