package com.management.inventory.yjinventorymanagement.domain;

import com.management.inventory.yjinventorymanagement.domain.Ingredient.Ingredient;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class Inventory {

    @Id
    @GeneratedValue
    private Long Id;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "inventory")
    private List<Ingredient> ingredients = new ArrayList<>();
}
