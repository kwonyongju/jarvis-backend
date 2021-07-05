package com.management.inventory.yjinventorymanagement.domain;

import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
public class Purchase {

    @Id
    @GeneratedValue
    @Column(name = "purchase_id")
    private Long Id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "person_id")
    private Person person;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "purchase")
    private List<Item> items = new ArrayList<>();

    private Long totalPriceInCent;
    private LocalDateTime purchaseDate;

    protected Purchase() {
    }

    public Purchase(Person person, List<Item> items) {
        this.person = person;
        this.items = items;
    }

    public static Purchase orderItems(Person person, List<Item> items) {
        Purchase purchase = new Purchase(person, items);

        Long totalPrice = items.stream().map(Item::getPriceInCent).reduce(0L, Long::sum);
        purchase.setTotalPriceInCent(totalPrice);
        purchase.setPurchaseDate(LocalDateTime.now());

        return purchase;
    }

    private void setTotalPriceInCent(Long totalPriceInCent) {
        this.totalPriceInCent = totalPriceInCent;
    }

    private void setPurchaseDate(LocalDateTime purchaseDate) {
        this.purchaseDate = purchaseDate;
    }
}
