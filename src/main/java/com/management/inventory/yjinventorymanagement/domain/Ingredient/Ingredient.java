package com.management.inventory.yjinventorymanagement.domain.Ingredient;

import com.management.inventory.yjinventorymanagement.domain.Inventory;
import com.management.inventory.yjinventorymanagement.domain.Item;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.*;

@DiscriminatorColumn(discriminatorType = DiscriminatorType.STRING,
        name="Ingredient_Type"
)
@Entity
public abstract class Ingredient {

    @Id
    @GeneratedValue
    @Column(name = "ingredient_id")
    private Long Id;

    private String name;
    private Long priceInCent;
    private int stockQuantity;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "inventory_id")
    private Inventory inventory;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ingredient")
//    private List<>
}
