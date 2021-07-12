package com.backend.jarvis.domain;

import com.backend.jarvis.domain.Ingredient.Ingredient;
import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @JsonIgnore
    @JoinColumn(name = "order_id")
    @ManyToOne(fetch = LAZY, cascade = CascadeType.PERSIST)
    @Setter
    private Order order;

    @JoinColumn(name = "ingredient_id")
    @ManyToOne(fetch = LAZY, cascade = CascadeType.PERSIST)
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
