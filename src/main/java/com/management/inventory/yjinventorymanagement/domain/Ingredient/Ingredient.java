package com.management.inventory.yjinventorymanagement.domain.Ingredient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.management.inventory.yjinventorymanagement.domain.Item;
import com.management.inventory.yjinventorymanagement.domain.OrderIngredient;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@DiscriminatorColumn(discriminatorType = DiscriminatorType.STRING,
        name = "Ingredient_Type"
)

@Entity
@Getter
@Table(name = "ingredient")
public abstract class Ingredient {

    @Id
    @GeneratedValue
    @Column(name = "ingredient_id")
    private Long Id;

    @Column(name = "ingredient_name", unique = true)
    private String name;
    private Long priceInCent;

    @JsonIgnore
    @ManyToOne(fetch = LAZY)
    private Item item;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ingredient")
    private List<OrderIngredient> orderIngredients = new ArrayList<>();

    public Ingredient() {
    }

    public Ingredient(String name, Long priceInCent) {
        this.name = name;
        this.priceInCent = priceInCent;
    }


}
