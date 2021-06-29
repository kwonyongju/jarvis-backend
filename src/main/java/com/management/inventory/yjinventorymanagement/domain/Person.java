package com.management.inventory.yjinventorymanagement.domain;

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

    @OneToMany(mappedBy = "person")
    private List<PurchaseHistory> purchaseHistories = new ArrayList<>();

    @OneToMany(mappedBy = "person")
    private List<Order> orders = new ArrayList<>();

    public Person() {}

    public Person(@NotNull String firstName, @NotNull String lastName, @NotNull String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }
}
