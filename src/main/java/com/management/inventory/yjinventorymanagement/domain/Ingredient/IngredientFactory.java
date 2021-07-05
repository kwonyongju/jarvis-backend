package com.management.inventory.yjinventorymanagement.domain.Ingredient;

import org.springframework.stereotype.Component;

@Component
public class IngredientFactory {

    public static Ingredient createIngredient(String ingredientName) {
        Ingredient ingredient;
        switch (ingredientName) {
            case "Bacon":
                ingredient = new Bacon("Bacon", 39L);
                break;
            case "Bun":
                ingredient = new Bun("Bun", 29L);
                break;
            case "Cheese":
                ingredient = new Cheese("Cheese", 89L);
                break;
            case "Lettuce":
                ingredient = new Lettuce("Lettuce", 10L);
                break;
            case "Patty":
                ingredient = new Patty("Patty", 599L);
                break;
            case "Tomato":
                ingredient = new Tomato("Tomato", 35L);
                break;
            default:
                throw new IllegalArgumentException("No such ingredient");
        }
        return ingredient;
    }
}
