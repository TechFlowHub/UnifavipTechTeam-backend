package com.unifavipTechTeam.favip.entity;

import com.unifavipTechTeam.favip.entity.Enums.*;

import jakarta.persistence.*;

@Entity
@Table(name = "diversity")
public class Diversity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "social_name")
    private String socialName;

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "sex")
    @Enumerated(EnumType.STRING)
    private Sex sex;

    @Column(name = "race")
    @Enumerated(EnumType.STRING)
    private Race race;

    @Column(name = "sexual_orientation")
    @Enumerated(EnumType.STRING)
    private SexualOrientation sexualOrientation;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "personal_data_id")
    private PersonalData personalData;

    public Diversity() {
    }

    public Diversity(Long id, String socialName, Gender gender, Sex sex, Race race, SexualOrientation sexualOrientation, PersonalData personalData) {
        this.id = id;
        this.socialName = socialName;
        this.gender = gender;
        this.sex = sex;
        this.race = race;
        this.sexualOrientation = sexualOrientation;
        this.personalData = personalData;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSocialName() {
        return socialName;
    }

    public void setSocialName(String socialName) {
        this.socialName = socialName;
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

    public PersonalData getPersonalData() {
        return personalData;
    }

    public void setPersonalData(PersonalData personalData) {
        this.personalData = personalData;
    }
}