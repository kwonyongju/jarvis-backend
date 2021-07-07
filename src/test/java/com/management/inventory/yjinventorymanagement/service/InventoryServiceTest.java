package com.management.inventory.yjinventorymanagement.service;

import com.management.inventory.yjinventorymanagement.domain.Item;
import com.management.inventory.yjinventorymanagement.domain.Person;
import com.management.inventory.yjinventorymanagement.domain.PurchaseItem;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
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

        inventoryService.addStock("Bun", 30);
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
        Item bigMac = itemService.createItem("big mac");

        List<PurchaseItem> purchaseItems = new ArrayList<>();
        PurchaseItem pi = PurchaseItem.createPurchaseItem(bigMac, 2);
        purchaseItems.add(pi);

        purchaseService.purchase(customer.getId(), purchaseItems);
        // big mac -> "bun", "lettuce", "cheese", "patty", "bun", "lettuce", "cheese", "patty", "bun"

        assertAll("inventory stock is properly removed after purchase",
                () -> assertThat(inventoryService.getStockQuantity("Bun")).isSameAs(27),
                () -> assertThat(inventoryService.getStockQuantity("Lettuce")).isSameAs(28),
                () -> assertThat(inventoryService.getStockQuantity("Cheese")).isSameAs(48),
                () -> assertThat(inventoryService.getStockQuantity("Patty")).isSameAs(28)
        );
    }
}