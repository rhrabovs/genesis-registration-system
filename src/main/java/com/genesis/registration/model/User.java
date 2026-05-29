package com.genesis.registration.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Jmeno musi byt vyplneno")
    @Column(nullable = false)
    private String name;

    @NotBlank(message = "Prijmeni musi byt vyplneno")
    @Column(nullable = false)
    private String surname;

    @NotBlank(message = "person ID musi byt vyplneno")
    @Column(name = "person_id", nullable = false, unique = true, length = 12 )
    private String personId;

    @Column(nullable = false, unique = true)
    private String uuid;

    public User() {}

    public User(String name, String surname, String personId, String uuid){
        this.name = name;
        this.surname = surname;
        this.personId = personId;
        this.uuid = uuid;
    }

    // getters, setters


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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    @PrePersist
    public void generateUuid(){
        if (this.uuid == null) {
            this.uuid = UUID.randomUUID().toString();
        }
    }
}
