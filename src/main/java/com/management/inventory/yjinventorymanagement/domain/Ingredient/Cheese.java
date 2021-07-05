package com.management.inventory.yjinventorymanagement.domain.Ingredient;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Cheese")
public class Cheese extends Ingredient {
    public Cheese(String name, Long priceInCent) {
        super(name, priceInCent);
    }

    public Cheese() {

    }
}
