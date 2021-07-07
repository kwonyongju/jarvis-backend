package com.management.inventory.yjinventorymanagement.constant;

import com.management.inventory.yjinventorymanagement.domain.Ingredient.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum ItemCatalog {
    HAMBURGER("The original burger starts with a 100% pure beef burger seasoned with just a pinch of salt and pepper.", 299L, new Bun(), new Patty(), new Bun()),
    CHEESE_BURGER("Our simple, classic cheeseburger begins with a 100% pure beef burger seasoned with just a pinch of salt and pepper.", 499L, new Bun(), new Cheese(), new Patty(), new Bun()),
    DOUBLE_CHEESE_BURGER("The McDonald's Double Cheeseburger features two 100% pure beef burger patties seasoned with just a pinch of salt and pepper.", 649L, new Bun(), new Cheese(), new Cheese(), new Patty(), new Bun()),
    BACON_CLUBHOUSE_BURGER("Thick-cut Apple-wood smoked bacon, white Cheddar, crisp leaf lettuce and fresh tomato, all lovingly layered on a quarter pound of 100% pure beef, then topped with Big Mac special sauce", 1099L, new Bun(), new Bacon(), new Patty(), new Cheese(), new Tomato(), new Lettuce(), new Tomato(), new Bacon(), new Bun()),
    BIG_MAC("Mouthwatering perfection starts with two 100% pure beef patties and Big Mac® sauce sandwiched between a sesame seed bun", 849L, new Bun(), new Lettuce(), new Cheese(), new Patty(), new Bun(), new Lettuce(), new Cheese(), new Patty(), new Bun()),
    DOUBLE_BIG_MAC("Made with four 100% Canadian beef patties, special sauce, crisp lettuce, processed cheddar cheese, and toasted sesame seed buns", 999L, new Bun(), new Lettuce(), new Cheese(), new Patty(), new Patty(), new Bun(), new Lettuce(), new Cheese(), new Patty(), new Patty(), new Bun());

    private String description;
    private Ingredient[] ingredients;
    private Long priceInCent;

    ItemCatalog(String description, Long priceInCent, Ingredient... ingredients) {
        this.description = description;
        this.ingredients = ingredients;
        this.priceInCent = priceInCent;
    }

    public static String convertToEnumCase(String name) {
        return name.replaceAll(" ", "_").toUpperCase();
    }

    public String getDescription() {
        return description;
    }

    public Ingredient[] getIngredients() {
        return ingredients;
    }

    public List<String> getIngredientsList() {
        return Arrays.asList(ingredients)
                .stream()
                .map(i -> i.getClass().getSimpleName())
                .collect(Collectors.toList());
    }

    public Long getPriceInCent() {
        return priceInCent;
    }

    public String getFormattedName() {
        String removedUnderScore = this.name()
                .replaceAll("_", " ")
                .toLowerCase();
        return removedUnderScore.substring(0, 1).toUpperCase() + removedUnderScore.substring(1);
    }
}
