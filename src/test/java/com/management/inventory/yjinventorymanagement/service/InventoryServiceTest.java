package com.management.inventory.yjinventorymanagement.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

@SpringBootTest
@Transactional
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class InventoryServiceTest {

    @Autowired
    InventoryService inventoryService;
    @Autowired
    IngredientService ingredientService;

    @BeforeAll
    void initialize() {
        inventoryService.addStock("bun", 30);
        inventoryService.addStock("cheese", 50);
    }

    @Test
    void addNewIngredientStock() {
        inventoryService.addStock("bacon", 20);

        Assertions.assertThat(inventoryService.getStockQuantity("bacon")).isSameAs(20);
    }

    @Test
    void addStockToExistingIngredient() {
        inventoryService.addStock("bun", 70);

        Assertions.assertThat(inventoryService.getStockQuantity("bun")).isSameAs(100);
    }

    @Test
    void removeStock() {
        inventoryService.removeStock("cheese");

        Assertions.assertThat(inventoryService.getStockQuantity("cheese")).isSameAs(49);
    }
}