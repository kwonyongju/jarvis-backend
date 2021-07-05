package com.management.inventory.yjinventorymanagement.service;

import com.management.inventory.yjinventorymanagement.domain.Ingredient.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class IngredientServiceTest {

    @Autowired
    IngredientService ingredientService;

    @Test
    void addIngredient() {
        Ingredient bacon = new Bacon("Bacon", 39L);
        Ingredient bun = new Bun("Bun", 29L);
        Ingredient cheese = new Cheese("Cheese", 89L);
        Ingredient lettuce = new Lettuce("Lettuce", 10L);
        Ingredient patty = new Patty("Patty", 599L);
        Ingredient tomato = new Tomato("Tomato", 35L);
        ingredientService.add(bacon, bun, cheese, lettuce, patty, tomato);

        List<Ingredient> ingredients = ingredientService.getAllIngredients();

        Assertions.assertThat(ingredients.size()).isSameAs(6);
    }
}