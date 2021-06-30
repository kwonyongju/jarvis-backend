package com.management.inventory.yjinventorymanagement.domain;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Entity
public class PurchaseHistory {

    @Id
    @GeneratedValue
    @Column(name = "purchase_id")
    private Long Id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "person_id")
    private Person person;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "purchase")
    private List<Item> items = new ArrayList<>();

    private Long totalPriceInCent;
    private LocalDateTime purchaseDate;
}
