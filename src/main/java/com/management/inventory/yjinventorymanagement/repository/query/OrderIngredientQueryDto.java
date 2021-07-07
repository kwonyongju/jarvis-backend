package com.management.inventory.yjinventorymanagement.repository.query;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.management.inventory.yjinventorymanagement.domain.Ingredient.Ingredient;
import com.management.inventory.yjinventorymanagement.domain.OrderIngredient;
import lombok.Data;

@Data
public class OrderIngredientQueryDto {

    @JsonIgnore
    private Long orderIngredientId;
    private Ingredient ingredient;
    private Long totalPriceInCent;
    private int count;

    public OrderIngredientQueryDto(OrderIngredient orderIngredient) {
        this.orderIngredientId = orderIngredient.getId();
        this.ingredient = orderIngredient.getIngredient();
        this.totalPriceInCent = orderIngredient.getTotalPriceInCent();
        this.count = orderIngredient.getQuantity();
    }
}
