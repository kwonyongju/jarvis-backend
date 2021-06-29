package com.management.inventory.yjinventorymanagement.domain;

import com.management.inventory.yjinventorymanagement.domain.Ingredient.Ingredient;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Item {

    @Id
    @GeneratedValue
    private Long Id;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "item")
    private List<Ingredient> ingredients = new ArrayList<Ingredient>();

    private Long price;
}
