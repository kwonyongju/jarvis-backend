package com.management.inventory.yjinventorymanagement.domain.Ingredient;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Tomato")
public class Tomato extends Ingredient {
    public Tomato(String name, Long priceInCent) {
        super(name, priceInCent);
    }

    public Tomato() {

    }
}
