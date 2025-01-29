package com.unifavipTechTeam.favip.entity;

import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "personal_data")
public class PersonalData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "contact_email", nullable = false)
    private String contactEmail;

    @Column(name = "github_link")
    private String githubLink;

    @Column(name = "linkedin_link")
    private String linkedinLink;

    @Column(name = "disability", nullable = false)
    private Boolean disability;

    @Column(name = "disability_description")
    private String disabilityDescription;

    @Column(name = "birth_date", nullable = false)
    private String birthDate;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @OneToOne
    @JoinColumn(name = "diversity_id", referencedColumnName = "id")
    private Diversity diversity;

    @OneToOne
    @JoinColumn(name = "courses_id", referencedColumnName = "id")
    private Courses courses;

    @OneToMany(mappedBy = "personalData")
    private List<Experience> experiences;

    @OneToMany(mappedBy = "personalData")
    private List<Formation> formations;
}
