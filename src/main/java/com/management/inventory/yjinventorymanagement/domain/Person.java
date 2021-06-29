package com.management.inventory.yjinventorymanagement.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Person {

    @Id
    @GeneratedValue
    @Column(name = "person_id")
    private Long id;

    @OneToMany(mappedBy = "person")
    private List<PurchaseHistory> purchaseHistories = new ArrayList<>();

    @OneToMany(mappedBy = "person")
    private List<Order> orders = new ArrayList<>();
}
