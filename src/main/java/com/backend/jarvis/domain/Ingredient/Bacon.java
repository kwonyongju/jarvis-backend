package com.backend.jarvis.domain.Ingredient;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Bacon")

public class Bacon extends Ingredient {
    public Bacon(Long priceInCent) {
        super(priceInCent);
    }

    public Bacon() {

    }
}
