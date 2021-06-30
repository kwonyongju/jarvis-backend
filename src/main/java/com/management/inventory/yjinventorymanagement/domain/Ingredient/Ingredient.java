package com.management.inventory.yjinventorymanagement.domain.Ingredient;

import com.management.inventory.yjinventorymanagement.domain.Inventory;
import com.management.inventory.yjinventorymanagement.domain.Item;
import com.management.inventory.yjinventorymanagement.domain.Menu;
import com.management.inventory.yjinventorymanagement.exception.NotEnoughStockException;
import lombok.Getter;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.*;

@DiscriminatorColumn(discriminatorType = DiscriminatorType.STRING,
        name="Ingredient_Type"
)

@Entity
@Getter
public abstract class Ingredient {

    @Id
    @GeneratedValue
    @Column(name = "ingredient_id")
    private Long Id;

    @Column(name = "ingredient_name", unique = true)
    private String name;
    private Long priceInCent;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "inventory_id")
    private Inventory inventory;

    public Ingredient() {}

    public Ingredient(String name, Long priceInCent) {
        this.name = name;
        this.priceInCent = priceInCent;
    }
}
