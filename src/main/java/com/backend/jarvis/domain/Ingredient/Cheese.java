package com.backend.jarvis.domain.Ingredient;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Cheese")
public class Cheese extends Ingredient {
    public Cheese(Long priceInCent) {
        super(priceInCent);
    }

    public Cheese() {

    }
}
