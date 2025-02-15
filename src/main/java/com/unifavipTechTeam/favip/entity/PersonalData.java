package com.unifavipTechTeam.favip.entity;

import java.sql.Date;
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
    private Date birthDate;

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

    @OneToMany(mappedBy = "receiver", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Star> receivedStars;


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

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getGithubLink() {
        return githubLink;
    }

    public void setGithubLink(String githubLink) {
        this.githubLink = githubLink;
    }

    public String getLinkedinLink() {
        return linkedinLink;
    }

    public void setLinkedinLink(String linkedinLink) {
        this.linkedinLink = linkedinLink;
    }

    public Boolean getDisability() {
        return disability;
    }

    public void setDisability(Boolean disability) {
        this.disability = disability;
    }

    public String getDisabilityDescription() {
        return disabilityDescription;
    }

    public void setDisabilityDescription(String disabilityDescription) {
        this.disabilityDescription = disabilityDescription;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Diversity getDiversity() {
        return diversity;
    }

    public void setDiversity(Diversity diversity) {
        this.diversity = diversity;
    }

    public Courses getCourses() {
        return courses;
    }

    public void setCourses(Courses courses) {
        this.courses = courses;
    }

    public List<Experience> getExperiences() {
        return experiences;
    }

    public void setExperiences(List<Experience> experiences) {
        this.experiences = experiences;
    }

    public List<Formation> getFormations() {
        return formations;
    }

    public void setFormations(List<Formation> formations) {
        this.formations = formations;
    }
}
