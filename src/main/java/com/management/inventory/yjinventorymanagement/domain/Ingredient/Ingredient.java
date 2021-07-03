package com.management.inventory.yjinventorymanagement.domain.Ingredient;

import lombok.Getter;

import javax.persistence.*;

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

    public Ingredient() {
    }

    public Ingredient(String name, Long priceInCent) {
        this.name = name;
        this.priceInCent = priceInCent;
    }
}
