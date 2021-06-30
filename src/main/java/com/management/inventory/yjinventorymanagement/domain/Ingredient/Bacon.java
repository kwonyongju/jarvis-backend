package com.management.inventory.yjinventorymanagement.domain.Ingredient;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Bacon")

public class Bacon extends Ingredient {
    public Bacon(String name, Long priceInCent) {
        super(name, priceInCent);
    }

    protected Bacon() {

    }
}
