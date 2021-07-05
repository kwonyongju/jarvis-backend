package com.management.inventory.yjinventorymanagement.domain;

import com.management.inventory.yjinventorymanagement.domain.Ingredient.Ingredient;
import com.management.inventory.yjinventorymanagement.domain.Ingredient.IngredientFactory;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@Table(name = "order_ingredient")
public class OrderIngredient {

    @Id
    @GeneratedValue
    @Column(name = "order_ingredient_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

//    @ManyToOne(fetch = LAZY)
//    @JoinColumn(name = "ingredient_id")
//    private Ingredient ingredient;

    private String ingredient;

    @Setter
    private Long totalPriceInCent;
    private int quantity;

    protected OrderIngredient() {
    }

    public OrderIngredient(String ingredient, int quantity) {
        this.ingredient = ingredient;
        this.quantity = quantity;
    }

//    public OrderIngredient(Ingredient ingredient, int quantity) {
//        this.ingredient = ingredient;
//        this.quantity = quantity;
//    }

    public static OrderIngredient createOrderIngredient(String ingredientName, int quantity) throws Exception {
        OrderIngredient orderIngredient = new OrderIngredient(ingredientName, quantity);
        Ingredient ingredient = IngredientFactory.createIngredient(ingredientName);
        orderIngredient.setTotalPriceInCent(ingredient.getPriceInCent() * quantity);

        return orderIngredient;
    }

}
