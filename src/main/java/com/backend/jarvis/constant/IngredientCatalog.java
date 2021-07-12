package com.backend.jarvis.constant;

public enum IngredientCatalog {
    BUN(29L),
    BACON(39L),
    CHEESE(89L),
    LETTUCE(10L),
    PATTY(599L),
    TOMATO(35L);

    private Long priceInCent;

    IngredientCatalog(Long priceInCent) {
        this.priceInCent = priceInCent;
    }

    public Long getPriceInCent() {
        return priceInCent;
    }
}
