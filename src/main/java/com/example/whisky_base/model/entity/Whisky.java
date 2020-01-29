package com.example.whisky_base.model.entity;

import com.example.whisky_base.model.RegionOfProduction;
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

    @Enumerated(value = EnumType.STRING)
    RegionOfProduction regionOfProduction;

    @ManyToOne
    Distillery distillery;

//    public static Buildier buildier() {
//
//        return new Buildier();
//    }

//    private Whisky(Buildier buildier) {
//        this.regionOfProduction = buildier.regionOfProduction;
//        this.typeOfWhisky = buildier.typeOfWhisky;
//        this.distillery = buildier.distillery;
//        this.name = buildier.name;
//    }

//    public static class Buildier {
//        UUID id;
//        String name;
//        TypeOfWhisky typeOfWhisky;
//        RegionOfProduction regionOfProduction;
//        Distillery distillery;
//
//        private Buildier() {
//        }
//
//        public Buildier id(UUID id) {
//            this.id = id;
//            return this;
//        }
//
//        public Buildier name(String name) {
//            this.name = name;
//            return this;
//        }
//
//        public Buildier typeOfWhisky(TypeOfWhisky typeOfWhisky) {
//            this.typeOfWhisky = typeOfWhisky;
//            return this;
//        }
//
//        public Buildier regionOfProduction(RegionOfProduction regionOfProduction) {
//            this.regionOfProduction = regionOfProduction;
//            return this;
//        }
//
//        public Buildier distillery(Distillery distillery) {
//            this.distillery = distillery;
//            return this;
//        }
//
//        public Whisky build() {
//            return new Whisky(this);
//        }
//
//    }


}
