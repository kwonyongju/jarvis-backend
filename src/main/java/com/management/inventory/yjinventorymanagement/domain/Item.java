package com.management.inventory.yjinventorymanagement.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@Table(name = "item")
public class Item {

    @Id
    @GeneratedValue
    @Column(name = "item_id")
    private Long Id;

    @NotNull
    private String name;

    @JsonIgnore
    @ManyToOne(fetch = LAZY)
    private Menu menu;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "item")
    private List<PurchaseItem> purchaseItems = new ArrayList<>();

    private Long priceInCent;

    protected Item() {
    }

    public Item(@NotNull String name, Long priceInCent) {
        this.name = name;
        this.priceInCent = priceInCent;
    }
}
