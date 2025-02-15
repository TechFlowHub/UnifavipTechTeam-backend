package com.unifavipTechTeam.favip.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "stars")
public class Star {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "giver_id", nullable = false)
    private User giver;

    @ManyToOne
    @JoinColumn(name = "receiver_id", nullable = false)
    private PersonalData receiver;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getGiver() {
        return giver;
    }

    public void setGiver(User giver) {
        this.giver = giver;
    }

    public PersonalData getReceiver() {
        return receiver;
    }

    public void setReceiver(PersonalData receiver) {
        this.receiver = receiver;
    }
}
