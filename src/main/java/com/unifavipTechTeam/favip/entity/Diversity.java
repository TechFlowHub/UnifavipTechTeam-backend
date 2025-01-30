package com.unifavipTechTeam.favip.entity;

import jakarta.persistence.*;

@Entity
public class Diversity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    private Sex sex;

    @Enumerated(EnumType.STRING)
    private Race race;

    @Enumerated(EnumType.STRING)
    private SexualOrientation sexualOrientation;

    @OneToOne(mappedBy = "diversity")
    private PersonalData personalData;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public Race getRace() {
        return race;
    }

    public void setRace(Race race) {
        this.race = race;
    }

    public SexualOrientation getSexualOrientation() {
        return sexualOrientation;
    }

    public void setSexualOrientation(SexualOrientation sexualOrientation) {
        this.sexualOrientation = sexualOrientation;
    }
}

enum Gender {
    MALE, FEMALE, NON_BINARY, OTHER
}

enum Sex {
    MALE, FEMALE, INTERSEX
}

enum Race {
    WHITE, BLACK, ASIAN, HISPANIC, NATIVE_AMERICAN, OTHER
}

enum SexualOrientation {
    HETEROSEXUAL, HOMOSEXUAL, BISEXUAL, PANSEXUAL, ASEXUAL, OTHER
}