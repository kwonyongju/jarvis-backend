package com.backend.jarvis.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Person {

    @Id
    @GeneratedValue
    @Column(name = "person_id")
    private Long id;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    private String email;

    @JsonIgnore
    @OneToMany(mappedBy = "person")
    private List<Purchase> purchases = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "person")
    private List<Order> orders = new ArrayList<>();

    public Person() {
    }

    public Person(@NotNull String firstName, @NotNull String lastName, @NotNull String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }
}

