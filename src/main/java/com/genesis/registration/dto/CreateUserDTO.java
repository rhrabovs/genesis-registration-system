package com.genesis.registration.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CreateUserDTO {

    @NotBlank(message = "Jmeno musi byt vyplneno")
    @Size(min = 2, max = 50)
    private String name;

    @NotBlank(message = "Prijmeni musi byt vyplneno")
    @Size(min = 2, max = 50)
    private String surname;

    @NotBlank(message = "personId nemuze byt prazdne")
    @Size(min = 12, max = 12, message = "personId musi mit 12 znaku")
    private String personId;

    public CreateUserDTO() {
    }

    public CreateUserDTO(String name, String surname, String personId) {
        this.name = name;
        this.surname = surname;
        this.personId = personId;
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
}
