package com.management.inventory.yjinventorymanagement.constant;

public enum Recipe {
    HAMBURGER("The original burger starts with a 100% pure beef burger seasoned with just a pinch of salt and pepper. ", "bun", "patty", "bun"),
    CHEESE_BURGER("Our simple, classic cheeseburger begins with a 100% pure beef burger seasoned with just a pinch of salt and pepper.", "bun", "cheese", "patty", "bun"),
    DOUBLE_CHEESE_BURGER("The McDonald's Double Cheeseburger features two 100% pure beef burger patties seasoned with just a pinch of salt and pepper.",  "bun", "cheese", "cheese", "patty", "bun"),
    BACON_CLUBHOUSE_BURGER("Thick-cut Apple-wood smoked bacon, white Cheddar, crisp leaf lettuce and fresh tomato, all lovingly layered on a quarter pound of 100% pure beef, then topped with Big Mac special sauce", "bun", "patty", "cheese", "tomato", "lettuce", "tomato", "bun"),
    BIG_MAC("Mouthwatering perfection starts with two 100% pure beef patties and Big Mac® sauce sandwiched between a sesame seed bun", "bun", "lettuce", "cheese", "patty", "bun", "lettuce", "cheese", "patty", "bun"),
    DOUBLE_BIG_MAC("Made with four 100% Canadian beef patties, special sauce, crisp lettuce, processed cheddar cheese, and toasted sesame seed buns", "bun", "lettuce", "cheese", "patty", "patty", "bun", "lettuce", "cheese", "patty", "patty", "bun");

    private String description;
    private String[] ingredients;

    Recipe(String description, String... ingredients) {
        this.description = description;
        this.ingredients = ingredients;
    }

    public String getDescription() {
        return description;
    }

    public String[] getIngredients() {
        return ingredients;
    }

    public String getFormattedName() {
        String removedUnderScore = this.name()
                .replaceAll("_", " ")
                .toLowerCase();
        return removedUnderScore.substring(0, 1).toUpperCase() + removedUnderScore.substring(1);
    }
}
