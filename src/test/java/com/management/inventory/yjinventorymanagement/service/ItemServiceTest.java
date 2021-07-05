package com.management.inventory.yjinventorymanagement.service;

import com.management.inventory.yjinventorymanagement.domain.Item;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ItemServiceTest {

    @Autowired
    InventoryService inventoryService;
    @Autowired
    ItemService itemService;

    @Test
    void ingredientGetRemovedFromInventoryAfterItemCreated() {
        inventoryService.addStock("Bun", 30);
        inventoryService.addStock("Cheese", 50);
        inventoryService.addStock("Bacon", 20);
        inventoryService.addStock("Lettuce", 30);
        inventoryService.addStock("Patty", 30);


        Item bigMac = itemService.createItem("big mac");

        assertAll("inventory stock is properly removed after purchase",
                () -> assertThat(inventoryService.getStockQuantity("Bun")).isSameAs(27),
                () -> assertThat(inventoryService.getStockQuantity("Lettuce")).isSameAs(28),
                () -> assertThat(inventoryService.getStockQuantity("Cheese")).isSameAs(48),
                () -> assertThat(inventoryService.getStockQuantity("Patty")).isSameAs(28)
        );
    }
}