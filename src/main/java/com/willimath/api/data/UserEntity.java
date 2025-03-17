package com.willimath.api.data;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "user", schema = "public")
public class UserEntity {

    @Id
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "email")
    private String email;

    @Column(name = "birthdate")
    private LocalDate birth_date;

    @Column(name = "phone_number")
    private String phone_number;

    public UserEntity() {
    }

    public UserEntity(String name, String surname, String email, LocalDate birth_date, String phone_number) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.birth_date = birth_date;
        this.phone_number = phone_number;
    }

    public UserEntity(UUID id, String name, String surname, String email, LocalDate birth_date, String phone_number) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.birth_date = birth_date;
        this.phone_number = phone_number;
    }

    public UUID getId() {
        return id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(LocalDate birth_date) {
        this.birth_date = birth_date;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }
}
