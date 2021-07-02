package com.management.inventory.yjinventorymanagement.service;

import com.management.inventory.yjinventorymanagement.domain.Person;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@SpringBootTest
@Transactional
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class InventoryServiceTest {

    @Autowired
    InventoryService inventoryService;
    @Autowired
    IngredientService ingredientService;
    @Autowired
    PurchaseService purchaseService;
    @Autowired
    PersonService personService;

    private Person customer;

    @BeforeAll
    void initialize() {
        customer = new Person("Yongju", "Kwon", "yongjuKwon@gmail.com");
        personService.register(customer);

        inventoryService.addStock("bun", 30);
        inventoryService.addStock("cheese", 50);

    }

    @Test
    void addNewIngredientStock() {
        inventoryService.addStock("bacon", 20);

        assertThat(inventoryService.getStockQuantity("bacon")).isSameAs(20);
    }

    @Test
    void addStockToExistingIngredient() {
        inventoryService.addStock("bun", 70);

        assertThat(inventoryService.getStockQuantity("bun")).isSameAs(100);
    }

    @Test
    void removeStock() {
        inventoryService.removeStock("cheese");

        assertThat(inventoryService.getStockQuantity("cheese")).isSameAs(49);
    }

    @Test
    void stockIsRemovedAfterPurchase() {
        inventoryService.addStock("lettuce", 30);
        inventoryService.addStock("patty", 30);

        purchaseService.purchase(customer, "big mac");
        // big mac -> "bun", "lettuce", "cheese", "patty", "bun", "lettuce", "cheese", "patty", "bun"

        assertAll("inventory stock is properly removed after purchase",
                () -> assertThat(inventoryService.getStockQuantity("bun")).isSameAs(27),
                () -> assertThat(inventoryService.getStockQuantity("lettuce")).isSameAs(28),
                () -> assertThat(inventoryService.getStockQuantity("cheese")).isSameAs(48),
                () -> assertThat(inventoryService.getStockQuantity("patty")).isSameAs(28)
        );
    }
}