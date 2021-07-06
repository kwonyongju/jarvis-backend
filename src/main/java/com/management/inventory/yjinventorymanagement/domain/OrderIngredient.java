package com.management.inventory.yjinventorymanagement.domain;

import com.management.inventory.yjinventorymanagement.domain.Ingredient.Ingredient;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@Table(name = "order_ingredient")
public class OrderIngredient {

    @Id
    @GeneratedValue
    @Column(name = "order_ingredient_id")
    private Long id;

    @ManyToOne(fetch = LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "order_id")
    @Setter
    private Order order;

    @ManyToOne(fetch = LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "ingredient_id")
    private Ingredient ingredient;

    @Setter
    private Long totalPriceInCent;
    private int quantity;

    protected OrderIngredient() {
    }

    public OrderIngredient(Ingredient ingredient, int quantity) {
        this.ingredient = ingredient;
        this.quantity = quantity;
    }

    public static OrderIngredient createOrderIngredient(Ingredient ingredient, int quantity) {
        OrderIngredient orderIngredient = new OrderIngredient(ingredient, quantity);
        orderIngredient.setTotalPriceInCent(ingredient.getPriceInCent() * quantity);

        return orderIngredient;
    }
}
