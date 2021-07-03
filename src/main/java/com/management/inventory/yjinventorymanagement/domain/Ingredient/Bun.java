package com.management.inventory.yjinventorymanagement.domain.Ingredient;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Bun")
public class Bun extends Ingredient {
    public Bun(String name, Long priceInCent) {
        super(name, priceInCent);
    }

    protected Bun() {

    }
}
