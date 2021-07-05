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

        inventoryService.addStock("Bun", 30);
        inventoryService.addStock("Cheese", 50);
        inventoryService.addStock("Tomato", 50);
    }

    @Test
    void addIngredientToStock() {
        inventoryService.addStock("Bacon", 20);

        assertThat(inventoryService.getStockQuantity("Bacon")).isSameAs(20);
    }

    @Test
    void removeStock() {
        inventoryService.removeStock("Tomato");

        assertThat(inventoryService.getStockQuantity("Tomato")).isSameAs(49);
    }

    @Test
    @Transactional
    void stockIsRemovedAfterPurchase() {
        inventoryService.addStock("Lettuce", 30);
        inventoryService.addStock("Patty", 30);

        purchaseService.purchase(customer, "big mac");
        // big mac -> "bun", "lettuce", "cheese", "patty", "bun", "lettuce", "cheese", "patty", "bun"

        assertAll("inventory stock is properly removed after purchase",
                () -> assertThat(inventoryService.getStockQuantity("Bun")).isSameAs(27),
                () -> assertThat(inventoryService.getStockQuantity("Lettuce")).isSameAs(28),
                () -> assertThat(inventoryService.getStockQuantity("Cheese")).isSameAs(48),
                () -> assertThat(inventoryService.getStockQuantity("Patty")).isSameAs(28)
        );
    }
}