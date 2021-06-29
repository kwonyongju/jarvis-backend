package com.management.inventory.yjinventorymanagement.domain;

import com.management.inventory.yjinventorymanagement.domain.Ingredient.Ingredient;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.*;

@Entity
public class Item {

    @Id
    @GeneratedValue
    @Column(name = "item_id")
    private Long Id;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "item")
    private List<Ingredient> ingredients = new ArrayList<Ingredient>();

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "purchase_id")
    private PurchaseHistory purchase;

    private Long priceInCent;
}
