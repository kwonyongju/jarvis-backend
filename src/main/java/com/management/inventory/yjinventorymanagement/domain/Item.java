package com.management.inventory.yjinventorymanagement.domain;

import com.management.inventory.yjinventorymanagement.domain.Ingredient.Ingredient;
import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
public class Item {

    @Id
    @GeneratedValue
    @Column(name = "item_id")
    private Long Id;

    @NotNull
    private String name;
    private String description;

    @OneToMany(mappedBy = "item")
    private List<Ingredient> ingredients = new ArrayList<>(); // need to change string -> ingredient

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "menu_id")
    private Menu menu;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "purchase_id")
    private Purchase purchase;

    private Long priceInCent;

    public Item(@NotNull String name, Long priceInCent, List<Ingredient> ingredients) {
        this.name = name;
        this.ingredients = ingredients;
        this.priceInCent = priceInCent;
    }

    protected Item() {
    }


}
