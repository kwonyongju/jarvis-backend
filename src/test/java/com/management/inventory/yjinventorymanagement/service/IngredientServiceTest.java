package com.management.inventory.yjinventorymanagement.service;

import com.management.inventory.yjinventorymanagement.domain.Ingredient.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class IngredientServiceTest {

    @Autowired
    IngredientService ingredientService;

    @Test
    void addIngredient() {
        Ingredient bacon = new Bacon("bacon", 39L);
        Ingredient bun = new Bun("bun", 29L);
        Ingredient cheese = new Cheese("cheese", 89L);
        Ingredient lettuce = new Lettuce("lettuce", 10L);
        Ingredient patty = new Patty("patty", 599L);
        Ingredient tomato = new Tomato("tomato", 35L);
        ingredientService.add(bacon, bun, cheese, lettuce, patty, tomato);

        List<Ingredient> ingredients = ingredientService.findAll();

        Assertions.assertThat(ingredients.size()).isSameAs(6);
    }
}