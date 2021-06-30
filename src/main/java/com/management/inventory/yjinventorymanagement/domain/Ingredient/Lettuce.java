package com.management.inventory.yjinventorymanagement.domain.Ingredient;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Lettuce")
public class Lettuce extends Ingredient {
    public Lettuce(String name, Long priceInCent) {
        super(name, priceInCent);
    }

    protected Lettuce() {

    }
}
