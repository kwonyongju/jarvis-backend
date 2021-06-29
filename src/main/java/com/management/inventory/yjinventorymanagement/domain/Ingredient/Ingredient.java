package com.management.inventory.yjinventorymanagement.domain.Ingredient;

import javax.persistence.*;

@DiscriminatorColumn(discriminatorType = DiscriminatorType.STRING,
        name="Ingredient_Type"
)
@Entity
public abstract class Ingredient {

    @Id
    @GeneratedValue
    @Column(name = "ingredient_id")
    private Long Id;

    private String name;
    private Long priceInCent;
    private int stockQuantity;
}
