package com.management.inventory.yjinventorymanagement.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static javax.persistence.FetchType.*;

@Entity
public class Item {

    @Id
    @GeneratedValue
    @Column(name = "item_id")
    private Long Id;

    @NotNull
    private String name;
    private String description;

    @ElementCollection
    private List<String> ingredients = new ArrayList<>();

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "menu_id")
    private Menu menu;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "purchase_id")
    private Purchase purchase;

    private Long priceInCent;

    public Item(@NotNull String name, Long priceInCent, List<String> ingredients) {
        this.name = name;
        this.ingredients = ingredients;
        this.priceInCent = priceInCent;
    }

    protected Item() {
    }

    public static Item createItem(Inventory inventory, String name, Long priceInCent, String... ingredients) {
        // Remove stock from inventory if stock is enough
        for (String ingredient: ingredients)
            inventory.removeStock(ingredient);

        List<String> ingredientsList = Arrays.asList(ingredients);
        Item item = new Item(name, priceInCent, ingredientsList);

        return item;
    }
}
