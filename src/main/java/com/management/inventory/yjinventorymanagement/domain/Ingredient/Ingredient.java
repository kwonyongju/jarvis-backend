package com.management.inventory.yjinventorymanagement.domain.Ingredient;

import javax.persistence.*;

@DiscriminatorColumn(discriminatorType = DiscriminatorType.STRING,
        name="Ingredient_Type"
)
@Entity
public abstract class Ingredient {

    @Id
    @GeneratedValue
    private Long Id;

    private Long price;

    private int quantity;
}
