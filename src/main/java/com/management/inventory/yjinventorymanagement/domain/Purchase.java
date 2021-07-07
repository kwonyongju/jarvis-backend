package com.management.inventory.yjinventorymanagement.domain;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@Slf4j
public class Purchase {

    @Id
    @GeneratedValue
    @Column(name = "purchase_id")
    private Long Id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "person_id")
    private Person person;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "purchase")
    private List<PurchaseItem> purchaseItems = new ArrayList<>();

    private Long totalPriceInCent;
    private LocalDateTime purchaseDate;

    protected Purchase() {
    }

    public Purchase(Person person, List<PurchaseItem> purchaseItems) {
        this.person = person;
        this.purchaseDate = LocalDateTime.now();
        Long totalPrice = 0L;

        for (PurchaseItem pi : purchaseItems) {
            addPurchaseItem(pi);
            totalPrice += pi.getTotalPriceInCent();
        }

        this.totalPriceInCent = totalPrice;
    }

    public static Purchase purchase(Person person, List<PurchaseItem> purchaseItems) {
        Purchase purchase = new Purchase(person, purchaseItems);

        return purchase;
    }

    private void addPurchaseItem(PurchaseItem purchaseItem) {
        this.purchaseItems.add(purchaseItem);
        purchaseItem.setPurchase(this);
    }
}
