package com.management.inventory.yjinventorymanagement.domain.Ingredient;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Patty")
public class Patty extends Ingredient {
    public Patty(Long priceInCent) {
        super(priceInCent);
    }

    public Patty() {

    }
}
