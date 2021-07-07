package com.management.inventory.yjinventorymanagement.domain.Ingredient;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Tomato")
public class Tomato extends Ingredient {
    public Tomato(Long priceInCent) {
        super(priceInCent);
    }

    public Tomato() {

    }
}
