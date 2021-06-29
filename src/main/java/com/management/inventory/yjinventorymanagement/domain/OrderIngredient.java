package com.management.inventory.yjinventorymanagement.domain;

import com.management.inventory.yjinventorymanagement.domain.Ingredient.Ingredient;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
public class OrderIngredient {

    @Id
    @GeneratedValue
    @Column(name = "order_ingredient_id")
    private Long id;

//    @ManyToOne(fetch = LAZY)
//    @JoinColumn(name = "order_id")
//    private Order order;
//
//    @ManyToOne(fetch = LAZY)
//    @JoinColumn(name = "ingredient_id")
//    private Ingredient ingredient;

    private Long priceInCent;
    private int quantity;
}
