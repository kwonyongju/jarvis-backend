package com.management.inventory.yjinventorymanagement.domain.Ingredient;

import com.management.inventory.yjinventorymanagement.constant.IngredientCatalog;
import org.springframework.stereotype.Component;

@Component
public class IngredientFactory {

    public static Ingredient createIngredient(String ingredientName) {
        Ingredient ingredient;
        switch (ingredientName) {
            case "Bacon":
                ingredient = new Bacon(IngredientCatalog.BACON.getPriceInCent());
                break;
            case "Bun":
                ingredient = new Bun(IngredientCatalog.BUN.getPriceInCent());
                break;
            case "Cheese":
                ingredient = new Cheese(IngredientCatalog.CHEESE.getPriceInCent());
                break;
            case "Lettuce":
                ingredient = new Lettuce(IngredientCatalog.LETTUCE.getPriceInCent());
                break;
            case "Patty":
                ingredient = new Patty(IngredientCatalog.PATTY.getPriceInCent());
                break;
            case "Tomato":
                ingredient = new Tomato(IngredientCatalog.TOMATO.getPriceInCent());
                break;
            default:
                throw new IllegalArgumentException("No such ingredient");
        }
        return ingredient;
    }
}
