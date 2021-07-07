package com.management.inventory.yjinventorymanagement.service;

import com.management.inventory.yjinventorymanagement.domain.Person;
import com.management.inventory.yjinventorymanagement.exception.NotEnoughStockException;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
class InventoryServiceTest {

    @Autowired
    ItemService itemService;
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

        inventoryService.addStock("Bun", 6);
        inventoryService.addStock("Cheese", 50);
        inventoryService.addStock("Tomato", 50);
        inventoryService.addStock("Lettuce", 30);
        inventoryService.addStock("Patty", 30);
    }

    @Test
    @Order(1)
    @DisplayName("Check if inventory has proper stock after adding ingredients")
    void addIngredientToStock() {
        inventoryService.addStock("Bacon", 20);

        assertThat(inventoryService.getStockQuantity("Bacon")).isSameAs(20);
    }

    @Test
    @Order(2)
    @DisplayName("Check if inventory has proper stock after removing ingredients")
    void removeStock() {
        inventoryService.removeStock("Tomato");

        assertThat(inventoryService.getStockQuantity("Tomato")).isSameAs(49);
    }

    @Test
    @Order(3)
    @DisplayName("Check if stocks are properly removed after purchase items")
    @Transactional
    void stockIsRemovedAfterPurchase() {
        Map<String, Integer> items = new HashMap<>();
        items.put("big mac", 2);

        purchaseService.purchase(customer.getId(), items);
        assertAll("inventory stock is properly removed after purchase",
                () -> assertThat(inventoryService.getStockQuantity("Bun")).isSameAs(0),
                () -> assertThat(inventoryService.getStockQuantity("Lettuce")).isSameAs(26),
                () -> assertThat(inventoryService.getStockQuantity("Cheese")).isSameAs(46),
                () -> assertThat(inventoryService.getStockQuantity("Patty")).isSameAs(26)
        );
    }

    @Test
    @Order(4)
    void returnNotEnoughStockExceptionWhenStockIsOut() {
        for (int i = 0; i < 6; ++i)
            inventoryService.removeStock("Bun");

        assertThrows(
                NotEnoughStockException.class,
                () -> inventoryService.removeStock("Bun")
        );
    }
}